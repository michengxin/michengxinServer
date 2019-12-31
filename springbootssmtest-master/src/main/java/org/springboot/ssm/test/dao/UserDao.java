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

//     public User drawFirstPrize();
//     public void updateLotteryDraw();//根据用户id
 //    public void updateFirstDraw(@Param("id") String id);//根据用户id修改firstDraw =1
}
