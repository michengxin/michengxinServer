package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName RolePermission
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 17:13
 * @Version 1.0
 */
@Data
@SuppressWarnings("serial")
@TableName("t_sys_role_permission")
public class RolePermission extends SupperModel<RolePermission> {
    @TableField()
    String sysRoleId;//角色ID
    @TableField()
    String sysPermissionId;//权限ID
}
