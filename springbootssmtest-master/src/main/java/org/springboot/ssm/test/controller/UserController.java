package org.springboot.ssm.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@Controller
@CrossOrigin
public class UserController {

  //通过用户code和手机加密获取手机号码
  @RequestMapping("/deciphering")
  @ResponseBody
  public String deciphering(){

    System.out.println("1");
    return "1";


  }
}
