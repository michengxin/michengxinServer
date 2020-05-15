package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

/**
 * @ClassName Permission
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 16:14
 * @Version 1.0
 */
@Data
@TableName("t_sys_permission")
public class Permission extends SupperModel<Permission> {
    @TableField()
    String name;//资源名称
    @TableField()
    String type;//资源类型：menu,button,auth
    @TableField()
    String url;//访问url地址
    @TableField()
    String perCode;//权限代码字符串/menu=>vue name
    @TableField()
    String clazz;//样式
    @TableField()
    String parentId;//父结点id
    @TableField()
    String parentIds;//父结点id列表串
    @TableField()
    String sortString;//排序号
    @TableField()
    Integer available;//是否可用,1：可用，0不可用
    @TableField()
    String kind;//种类（1:网页端,2:移动端）

}