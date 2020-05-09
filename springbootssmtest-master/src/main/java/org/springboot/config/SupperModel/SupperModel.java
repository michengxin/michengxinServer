package org.springboot.config.SupperModel;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName SupperModel
 * @Description TODO
 * @Author mcx
 * @Date 2020/5/9 11:31
 * @Version 1.0
 */
public abstract class SupperModel <T extends SupperModel<?>> extends Model<T> {
    @TableId(
            value = "id",
            type = IdType.ASSIGN_ID
    )
    String id;
    @TableField(
            fill = FieldFill.INSERT
    )
    Date createTime;
    @TableField(
            fill = FieldFill.INSERT_UPDATE
    )
    Date lastUpdateTime;

    public SupperModel() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    protected Serializable pkVal() {
        return this.getId();
    }
}