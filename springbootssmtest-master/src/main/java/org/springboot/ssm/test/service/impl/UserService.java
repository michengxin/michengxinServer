package org.springboot.ssm.test.service.impl;

import org.springboot.ssm.test.dao.UserDao;
import org.springboot.ssm.test.entity.User;
import org.springboot.ssm.test.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService implements IUserService {
  @Autowired
  UserDao userDao;


//  //抽1个特等奖
//  public User drawFirstPrize(){
//    User user = userDao.drawFirstPrize();
//    //将抽中奖的人给标记，返回
//    userDao.updateStates(user.getId());
//    return user;
//  }
//  //抽5个一等奖
//  public List<User> drawTwoPrize(){
//    List<User> userList1 = userDao.drawFirstPrize();;//抽中奖的用户的集合
//    return userList1;
//  }
//  //抽10个二等奖
//  public List<User> drawThreePrize(){
//     return null;
//  }
//  //抽15个三等奖
//  public List<User> drawFourPrize(){
//    return null;
//  }

//初始化登录页面接口
  public String initLogin(String workNum,String telphone){
    //1.从localstorage中获取workNum和telphone 若为空,进入"登陆页面"
    //2.若不为空.通过workNum获取login_flag 是否为1 若为1 进入 "抽奖页面 带入用户信息",若不为1.进入"登陆"页面
    User user = new User();
    if(!"".equals(workNum) && workNum !=null){
      user = userDao.selectUserByWorkNum(workNum);
      if(user.getLoginFlag() == 1){
        return "true";
      }else{
        return "false";
      }
    }else{
      return "false";
    }
  }
  //登陆接口
  public String login(String workNum,String telphone){
       //1.判断该workNum是否存在用户表里 存在 进 2 不存在进3
      //2.判断workNum telphone 是否匹配,不匹配进4 匹配进5
       //判断该login_flag是否为1  判断 抽奖标记是否为1
      //3.返回该工号没有抽奖资格
      //4.返回密码错误
      //5.将该workNum的用户的login_flag改为1.返回抽奖页面.localstorage存入用户信息
      int count = userDao.selectCountByWorkNum(workNum);
      if(count>0){//判断该workNum是否存在用户表里
        int count1 = userDao.checkWorkNumAndTelphone(workNum,telphone);
        if(count1>0){//判断workNum telphone 是否匹配
          int loginFlag = userDao.checkLoginFlagByWorkNum(workNum);
          if(loginFlag == 1){
             int lotteryDrawFlag = userDao.checkLotteryDrawFlagByWorkNum(workNum);
             if(lotteryDrawFlag == 1){
                return "中奖名单页面";
             }else{
               return "抽奖页面"+workNum+","+telphone;
             }
          }else{
            userDao.updateLoginFlagByWorkNum(workNum);
            return "抽奖页面"+workNum+","+telphone;
          }
        }else{
          return "登陆页面"+"密码错误";
        }
      }else{
        return "登陆页面"+"该工号没有抽奖资格";
      }
  }
}
