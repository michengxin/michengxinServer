package org.springboot.ssm.test.service;

import org.springboot.ssm.test.entity.User;

import java.util.List;

public interface IUserService {
  public void init();
  public List<User> drawFirstPrize();
  public List<User> drawTwoPrize();
  public List<User> drawThreePrize();
  public List<User> drawFourPrize();
}
