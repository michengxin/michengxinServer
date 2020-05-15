package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName UserEnterprise
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 17:16
 * @Version 1.0
 */
@Data
@SuppressWarnings("serial")
@TableName("t_base_enterprise")
public class UserEnterprise extends SupperModel<UserEnterprise> {
    @TableField()
    String baseStaffId;//用户id
    @TableField()
    String companyId;//公司id
}