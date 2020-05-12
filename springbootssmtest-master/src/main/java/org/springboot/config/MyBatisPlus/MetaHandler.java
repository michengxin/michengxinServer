package org.springboot.config.MyBatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.SneakyThrows;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Calendar;
import java.util.Date;


/**
 * @ClassName MetaHandler
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/12 9:22
 * @Version 1.0
 */
@Component
public class MetaHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MetaHandler.class);

    /**
     * 新增数据执行
     *
     * @param metaObject
     */
    @SneakyThrows
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
    }
    /**
     * 更新数据执行
     * @param metaObject
     */
    @SneakyThrows
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("lastUpdateTime", new Date(), metaObject);
    }


}