package org.springboot.ssm.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.ssm.test.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
       public User selectUserByWorkNum(@Param("workNum") String workNum);
       public int selectCountByWorkNum(@Param("workNum") String workNum);
       public int checkWorkNumAndTelphone(@Param("workNum") String workNum,@Param("telphone") String telphone);
       public void updateLoginFlagByWorkNum(@Param("workNum") String workNum);
       public int checkLoginFlagByWorkNum(@Param("workNum") String workNum);
       public int checkLotteryDrawFlagByWorkNum(@Param("workNum") String workNum);
       public int updateLotteryDrawFlagByWorkNum(@Param("workNum") String workNum);
       public List<String> distributionOfFirstPrize();
       public int updateFirstDrawFlag(@Param("workNum") String workNum);
       public List<String> distributionOfTwoPrize();
       public int updateTwoDrawFlag(List<String> workNums);
       public List<String> distributionOfThreePrize();
       public int updateThreeDrawFlag(List<String> workNums);
       public List<String> distributionOfFourPrize();
       public int updateFourDrawFlag(List<String> workNums);
       public int importProjectList(List<User> userList);
}
