package org.springboot.service.impl;




import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springboot.entity.User;
import org.springboot.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class UserService {
  @Autowired
  UserDao userDao;



  @Transactional
  public PageInfo<User> selectUserByWorkNum() {
    PageHelper.startPage(1,2);//这行是重点，表示从pageNum页开始，每页pageSize条数据
    List<User> list = userDao.selectUserByWorkNum();
    PageInfo<User> pageInfo = new PageInfo<User>(list);
    return pageInfo;
  }
}
