package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName Test
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/18 13:41
 * @Version 1.0
 */
@Data
@SuppressWarnings("serial")
@TableName("t_test")
public class Test extends SupperModel<Test> {
    @TableField()
    String name;
}