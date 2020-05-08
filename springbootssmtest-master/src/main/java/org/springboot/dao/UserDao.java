package org.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.entity.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
       User selectUserByWorkNum();
}
