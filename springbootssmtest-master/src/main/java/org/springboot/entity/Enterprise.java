package org.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springboot.config.SupperModel.SupperModel;

import java.util.List;

/**
 * @ClassName Enterprise
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/14 16:53
 * @Version 1.0
 */
@Data
@TableName("t_base_enterprise")
public class Enterprise extends SupperModel<Enterprise> {

    String name;//公司名称

    String code;//公司编码

    Integer sort;  //排序值

    String isAll;//是否全资公司 1-全资公司  0-合资公司

    String registeName;//公司注册全称
    @TableField(exist = false)
    String label;//配合前端字段
    @TableField(exist = false)
    String value;//配合前端字段
    @TableField(exist = false)
    String departmentId;//配合前端字段
    @TableField()
    String cityAffiliation;//所属城市
    @TableField()
    String provincesSubordinate;//所属省份
    @TableField()
    String cityCode;//城市所在区号（后三位）
    @TableField(exist = false)
    String province;//省份
    @TableField(exist = false)
    String city;//市
}