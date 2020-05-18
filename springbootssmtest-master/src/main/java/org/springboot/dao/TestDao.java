package org.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springboot.config.SupperMapper;
import org.springboot.entity.Test;
@Mapper
public interface TestDao extends SupperMapper<Test> {
}
