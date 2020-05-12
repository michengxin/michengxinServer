package org.springboot.entity;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @author michengxin
 * @description
 * @date 2019/12/27/027
 */
@Data
@SuppressWarnings("serial")
@TableName("t_user")
public class User extends SupperModel<User> {
    @TableField()
    String username; //名字
    @TableField()
    String password;//密码
    @TableField()
    String salt;//加密盐
    @TableField()
    String userType;//用户类型
    @TableField(exist = false)
    String oldPassword;//旧密码
    @TableField(exist = false)
    String newPassword;//新密码
}
