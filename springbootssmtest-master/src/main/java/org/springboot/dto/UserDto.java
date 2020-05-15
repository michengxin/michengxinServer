package org.springboot.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springboot.entity.Department;
import org.springboot.entity.Enterprise;
import org.springboot.entity.Permission;
import org.springboot.entity.Role;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UserDto
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/15 9:24
 * @Version 1.0
 */
@Data
public class UserDto {
    @TableField()
    String userCode; //名字

    @TableField()
    String userType;//用户类型
    @TableField()
    String locked;//账号是否锁定，1：锁定，0未锁定
    @TableField()
    String name;//姓名
    @TableField()
    String email;//邮件
    @TableField()
    String phone;//电话
    @TableField()
    String openid;//微信openid
    @TableField()
    String userImg;//用户头像
    @TableField()
    String wehatNickname;//微信昵称
    @TableField()
    Date dimissionTime;//离职日期
    @TableField()
    Date entryTime;//入职日期

    @TableField(exist = false)
    List<Enterprise> enterprises;//公司list
    @TableField(exist = false)
    List<Department> departments;//部门list
    @TableField(exist = false)
    List<Role> roles;//角色list
    @TableField(exist = false)
    List<Permission> permissionMenus;//权限list(menu)
    @TableField(exist = false)
    List<Permission> permissionButtons;//权限list(button)
    @TableField(exist = false)
    String oldPassword;//旧密码
    @TableField(exist = false)
    String newPassword;//新密码
}