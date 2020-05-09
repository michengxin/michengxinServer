package org.springboot.entity;



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
@TableName("user")
public class User extends SupperModel<User> {
    String id;
    String name; //名字
    String workNum;//工号
    String telphone;//手机号码

}
