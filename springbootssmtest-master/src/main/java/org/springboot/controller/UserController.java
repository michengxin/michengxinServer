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
import org.springboot.service.impl.UserService;
import org.springboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  //用户登录
  @ApiOperation(value = "用户登录",notes = "根据username password 登录，返回 token")
  @ApiImplicitParams({
          @ApiImplicitParam(name = "username",value = "用户名",required = true),
          @ApiImplicitParam(name = "password",value = "密码",required = true)
  })
  @RequestMapping("/login")
  public RestResponseTable login(UserVo UserVo){
    LOGGER.info("日志打印测试");
    JSONObject jsonObject = new JSONObject();
    return new RestResponseTable(userService.selectUserByWorkNum());
  }

}
