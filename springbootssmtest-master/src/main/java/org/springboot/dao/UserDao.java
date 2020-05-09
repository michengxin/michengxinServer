package org.springboot.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import org.springboot.entity.User;


import java.util.List;

@Mapper
public interface UserDao {
       List<User> selectUserByWorkNum();
}
