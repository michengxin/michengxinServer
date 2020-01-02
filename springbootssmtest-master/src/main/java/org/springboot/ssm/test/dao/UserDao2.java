package org.springboot.ssm.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.ssm.test.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao2 {
    List<User> queryNoLotteryEightPeople();//抽8个人抽奖

    int updateEightType(@Param("userList") List<User> list, @Param("eightType") int eightType);//修改8人游戏状态

}
