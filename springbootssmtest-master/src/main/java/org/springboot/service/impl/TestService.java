package org.springboot.service.impl;

import org.springboot.dao.TestDao;
import org.springboot.entity.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/18 11:45
 * @Version 1.0
 */
@Service
public class TestService {
   @Resource
   TestDao testDao;

   public int insertTest(){
       Test test = new Test();
       test.setName("test");
       return testDao.insert(test);
   }

}