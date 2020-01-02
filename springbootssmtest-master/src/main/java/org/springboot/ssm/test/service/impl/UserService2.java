package org.springboot.ssm.test.service.impl;

import org.springboot.ssm.test.dao.UserDao2;
import org.springboot.ssm.test.entity.User;
import org.springboot.ssm.test.service.IUserService;
import org.springboot.ssm.test.service.IUserService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService2 implements IUserService2 {

  @Autowired
  UserDao2 userDao2;
  public List<User> randomOfPeople(){
    List<User> list = userDao2.queryNoLotteryEightPeople();
    if(list != null && list.size()>7){
      //修改状态
      int eightType = 1;
      userDao2.updateEightType(list,eightType);
    }else {
      userDao2.updateEightType(null,0);
      list = userDao2.queryNoLotteryEightPeople();
    }
   return  list;
  }



}
