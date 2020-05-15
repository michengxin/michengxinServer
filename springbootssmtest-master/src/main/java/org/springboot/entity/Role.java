package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName Role
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 16:07
 * @Version 1.0
 */
@Data
@TableName("t_sys_role")
public class Role extends SupperModel<Role> {
    @TableField()
    String code;        //角色代码
    @TableField()
    String name;        //名称
    @TableField()
    String available;  //是否可用,1：可用，0不可用
    @TableField(exist = false)
    String permissionId;//资源ID(,分格)
    @TableField(exist = false)
    String permissionName;//资源(,分格)
}