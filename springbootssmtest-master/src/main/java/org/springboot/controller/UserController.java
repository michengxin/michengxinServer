package org.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.config.BaseController.abstrac.BaseController;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.config.ResponseTable.clas.RestResponseTable;
import org.springboot.config.jwt.JwtToken;
import org.springboot.config.redis.RedisEnum;
import org.springboot.dao.UserDao;
import org.springboot.entity.User;
import org.springboot.service.impl.UserService;
import org.springboot.utils.MD5Util;
import org.springboot.utils.RedisUtils;
import org.springboot.utils.UUIDUtils;
import org.springboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
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
  JwtToken jwtToken;
  @Autowired
  RedisUtils redisUtils;
  @Resource
  UserDao userDao;

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
    Map<String, Object> claims = new HashMap<>();
    // 1. 验证用户名和密码
    // 2. 验证成功生成token
//    JSONObject jsonObject = new JSONObject();
//    jsonObject.put("token",jwtToken.generateToken(userId));
    return new RestResponseData(null);
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

}
