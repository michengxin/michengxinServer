package org.springboot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springboot.config.SupperMapper;
import org.springboot.entity.User;


import java.util.List;

@Mapper
public interface UserDao extends SupperMapper<User> {
       List<User> selectUserByWorkNum();
       int selectPhone(@Param("id") String id);
       User getUserInfo(@Param("username") String username,@Param("userType") String userType);
}
