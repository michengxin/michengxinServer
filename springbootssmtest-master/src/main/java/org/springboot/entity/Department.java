package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;



/**
 * @ClassName Department
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 16:42
 * @Version 1.0
 */
@Data
@TableName("t_base_department")
public class Department extends SupperModel<Department> {

    String depCode;             //部门编码
    String depName;             //部门名称
    String parentId;            //父级部门id
    String state;                //状态：1，启用。0，停用
    @TableField(fill = FieldFill.UPDATE)
    String remark;               //备注
    String remark1;              //备选字段1
    String remark2;              //备选字段2
    //    @TableField(exist = false)
//    String parentName;//不需要
    String companyId;//关联公司
    String type;//部门类型

    @TableField(exist = false)
    String label;//配合前端字段
    @TableField(exist = false)
    String value;//配合前端字段
}