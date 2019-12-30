package org.springboot.ssm.test.service.impl;

import org.springboot.ssm.test.entity.User;
import org.springboot.ssm.test.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class UserService implements IUserService {
  //从一群用户中存在一个数组 然后随机抽取 1个特等奖,5个一等奖,10个二等奖,15个三等奖
  List<User> userList = new ArrayList<User>();
  //初始化用户集合
  public void init() {
    for(int i =0;i<30;i++) {
      User user1 = new User();
      user1.id = ""+i;
      user1.name = "用户"+i;
      user1.nickName = "昵称"+i;
      user1.headSculpture = "头像"+i;
      user1.workNum = ""+i;
      user1.gameFlag = 0;
      user1.lotteryDrawFlag = 0;
      user1.joinFlag = 1;
      userList.add(user1);
    }
  }
  //抽1个特等奖
  public List<User> drawFirstPrize(){
    List<User> userList1 = new ArrayList<User>();//抽中奖的用户的集合
    int userListLenth = userList.size();
    Random rand = new Random();
    //取一个随机数 从  1-userListLenth 中 取 1个,取完后 匹配对应的用户,用户抽中特等奖和抽过奖的标记给上
    int randNumber =rand.nextInt(userListLenth - 1 + 1) + 1;
    User user = userList.get(randNumber);
    //给该user打上抽中特等奖和抽过奖的标记
    userList1.add(user);
    return userList1;
  }
  //抽5个一等奖
  public List<User> drawTwoPrize(){
    List<User> userList1 = new ArrayList<User>();//抽中奖的用户的集合
    int userListLenth = userList.size();
    Random rand = new Random();
    //取一个随机数 从  1-userListLenth 中 取 5个,取完后 匹配对应的用户,用户抽中特等奖和抽过奖的标记给上

    int randNumber =rand.nextInt(userListLenth - 1 + 1) + 1;
    User user = userList.get(randNumber);
    userList1.add(user);
    return userList1;
  }
  //抽10个二等奖
  public List<User> drawThreePrize(){
     return null;
  }
  //抽15个三等奖
  public List<User> drawFourPrize(){
    return null;
  }

}
