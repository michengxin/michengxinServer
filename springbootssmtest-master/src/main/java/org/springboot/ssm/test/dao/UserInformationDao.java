package org.springboot.ssm.test.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springboot.ssm.test.entity.UserInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserInformationDao {

    List<UserInformation> selectAll();
}