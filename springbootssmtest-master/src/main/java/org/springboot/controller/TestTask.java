package org.springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springboot.dao.TestDao;
import org.springboot.service.impl.TestService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.testng.annotations.Test;

import javax.annotation.Resource;

/**
 * @ClassName TestTask
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/18 11:43
 * @Version 1.0
 */
@Slf4j
@EnableScheduling
@Configuration
public class TestTask {
    @Resource
    TestService testService;
//    @Scheduled(cron = "*/5 * * * * ?")
    public void insertTest(){
        log.info("测试插入test数据开始");
        testService.insertTest();
        log.info("测试插入test数据结束");
    }
}