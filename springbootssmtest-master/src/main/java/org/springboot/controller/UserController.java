package org.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.config.BaseController.abstrac.BaseController;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.config.redis.RedisEnum;
import org.springboot.dao.UserDao;
import org.springboot.entity.User;
import org.springboot.service.impl.AuthService;
import org.springboot.service.impl.UserService;
import org.springboot.utils.MD5Util;
import org.springboot.utils.RedisUtils;
import org.springboot.utils.UUIDUtils;
import org.springboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@CrossOrigin
@RestController()
@Api(tags = "用户API")
@RequestMapping("api/user")
@Slf4j
public class UserController extends BaseController {

  @Autowired
  UserService userService;

  @Autowired
  RedisUtils redisUtils;
  @Resource
  UserDao userDao;
  @Autowired
  AuthService authService;

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //测试redis
    @ApiOperation(value = "测试redis",notes = "测试redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true)
    })
  @RequestMapping(value = "/textRedis")
  public String textRedis( String id){
      log.info("开始测试");
    //查询缓存中是否存在
    boolean hasKey = redisUtils.exists(id);
    String str = "";
    if(hasKey){
      //获取缓存
      Object object =  redisUtils.get(id);
      log.info("从缓存获取的数据"+ object);
      str = object.toString();
    }else{
      //从数据库中获取信息
      log.info("从数据库中获取数据");
      str = userService.selectPhone(id);
      //数据插入缓存（set中的参数含义：key值，user对象，缓存存在时间10（long类型），时间单位）
      if("1".equals(str)){
          redisUtils.set(id, RedisEnum.USERTOKEN,10L, TimeUnit.MINUTES);
          log.info("数据插入缓存" + str);
      }else{
          log.info("缓存,数据库均没有该数据" + str);
      }
    }
    return str;
  }
  //测试JWT登录
  @ApiOperation(value = "测试JWT登录",notes = "测试JWT登录")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "username",value = "用户名",required = true),
          @ApiImplicitParam(name = "password",value = "密码",required = true)
  })
  @RequestMapping("/login")
  public RestResponseData login(UserVo UserVo){
    LOGGER.info("日志打印测试");
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("token",this.authService.login(UserVo));
    return new RestResponseData(jsonObject);
  }

  //测试JWT注册
  @ApiOperation(value = "测试JWT注册",notes = "测试JWT注册")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "username",value = "用户名",required = true),
          @ApiImplicitParam(name = "password",value = "密码",required = true)
  })
  @RequestMapping("/register")
  public RestResponseData register(User user){
    LOGGER.info("日志打印测试");
    String userSalt = UUIDUtils.generateUUID32Lower();
    //盐
    user.setSalt(userSalt);
    //密码加盐
    user.setPassword(MD5Util.encodeByMD5(MD5Util.encodeByMD5(user.getPassword()).concat(userSalt)));
    //插登录表t_ss_user
    int result = userService.insertUser(user);
    return new RestResponseData(user);
  }
  //测试mybatis修改
  @ApiOperation(value = "测试mybatis修改",notes = "测试mybatis修改")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "username",value = "用户名",required = true),
          @ApiImplicitParam(name = "oldPassword",value = "旧密码",required = true),
          @ApiImplicitParam(name = "newPassword",value = "新密码",required = true)
  })
  @RequestMapping("/update")
  public RestResponseData update(User user){
    LOGGER.info("日志打印测试");
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("username",user.getUsername());
    List<User> userList =  userDao.selectList(queryWrapper);
    User sysUser = userList.get(0);
    UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
    updateWrapper.eq("username",user.getUsername());
    String userSalt = null;
    if(StringUtils.isNotBlank(sysUser.getSalt())){
      userSalt= sysUser.getSalt();
    }else {
      userSalt =UUIDUtils.generateUUID32Lower();
    }
    String md5oldPwd = MD5Util.encodeByMD5(MD5Util.encodeByMD5(user.getOldPassword()));
    if (!sysUser.getPassword().contains(md5oldPwd)){
      return new RestResponseData("原密码错误");// 与原密码不匹配
    }
    sysUser.setPassword(MD5Util.encodeByMD5(MD5Util.encodeByMD5(user.getNewPassword())));
    //修改登录表t_ss_user
    int result = userDao.update(sysUser,updateWrapper);
    return new RestResponseData(user);
  }
}
