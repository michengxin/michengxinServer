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
    @TableField(exist = true)
    String username; //名字
    @TableField(exist = true)
    String password;//密码
    @TableField(exist = true)
    String salt;//加密盐
}
