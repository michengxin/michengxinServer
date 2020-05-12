package org.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springboot.entity.User;
import org.springboot.dao.UserDao;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
  @Resource
  UserDao userDao;

  public PageInfo<User> selectUserByWorkNum() {
    PageHelper.startPage(1,2);//这行是重点，表示从pageNum页开始，每页pageSize条数据
    List<User> list = userDao.selectUserByWorkNum();
    PageInfo<User> pageInfo = new PageInfo<User>(list);
    return pageInfo;
  }
  public String selectPhone(String id) {
    int count  = userDao.selectPhone(id);
    if(count>0){
      return "1";
    }else{
      return "0";
    }
  }
  public int insertUser(User user){
    return userDao.insert(user);
  }

}
