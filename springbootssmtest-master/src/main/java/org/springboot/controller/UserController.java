package org.springboot.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springboot.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@CrossOrigin
@RestController()
@RequestMapping("api/user")
@Slf4j
public class UserController {

  @Autowired
  UserService userService;
  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  //用户登录
  @RequestMapping("/login")
  public RestResponseData login(){
    LOGGER.info("日志打印测试");
    return new RestResponseData(userService.selectUserByWorkNum());
  }

}
