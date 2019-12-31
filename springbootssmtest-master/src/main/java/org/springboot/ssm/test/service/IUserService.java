package org.springboot.ssm.test.service;

import org.springboot.ssm.test.entity.User;

import java.util.List;

public interface IUserService {
//  public User drawFirstPrize();
//  public List<User> drawTwoPrize();
//  public List<User> drawThreePrize();
//  public List<User> drawFourPrize();
//    public User selectUserByWorkNum(String workNum);
//    public int selectCountByWorkNum(String workNum);
//    public int checkWorkNumAndTelphone(String workNum,String telphone);

    public String initLogin(String workNum,String telphone);
    public String login(String workNum,String telphone);

}
