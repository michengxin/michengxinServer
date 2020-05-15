package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName UserRole
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 17:07
 * @Version 1.0
 */
@Data
@SuppressWarnings("serial")
@TableName("t_sys_user_role")
public class UserRole extends SupperModel<UserRole> {
    @TableField()
    String sysUserId;//用户ID
    @TableField()
    String sysRoleId;//角色ID
}