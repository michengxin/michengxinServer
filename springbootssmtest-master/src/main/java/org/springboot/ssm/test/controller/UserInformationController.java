package org.springboot.ssm.test.controller;

import org.springboot.ssm.test.service.IUserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserInformationController {

    @Autowired
    IUserInformationService userInformationService;

    @RequestMapping("/users")
    @ResponseBody
    public Object selectAll(){
        return userInformationService.selectAll();
    }
}
