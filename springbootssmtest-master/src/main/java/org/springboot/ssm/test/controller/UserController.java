package org.springboot.ssm.test.controller;


import org.springboot.ssm.test.entity.User;
import org.springboot.ssm.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@CrossOrigin
@Controller
public class UserController {

  @Autowired
  IUserService userService;
  //初始化登录页面
  @RequestMapping("/initLogin")
  @ResponseBody
  public String initLogin(String workNum,String telphone){
    return userService.initLogin(workNum,telphone);
  }
  //用户登录
  @RequestMapping("/login")
  @ResponseBody
  public String login(String workNum,String telphone){
    return userService.login(workNum,telphone);
  }
  //点击抽奖按钮


}
