package org.springboot.config.ActiviMq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @ClassName BeanConfig
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/19 9:01
 * @Version 1.0
 */

@Configuration
public class BeanConfig {

    //定义存放消息的队列
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("ActiveMQQueue");
    }
    //定义存放消息的队列
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("ActiveMQTopic");
    }

}