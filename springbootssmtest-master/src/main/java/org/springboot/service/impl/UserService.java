package org.springboot.service.impl;

import org.springboot.entity.User;
import org.springboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class UserService {
  @Autowired
  UserDao userDao;



  @Transactional
  public User selectUserByWorkNum() {
    return userDao.selectUserByWorkNum();
  }
}
