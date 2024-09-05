package com.lh.cloud.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: lh
 * @date: 2022/9/30 15:05
 */
@Data
public class BaseEntity implements Serializable {
    /**
     * 租户id
     */
    @TableField("tenant_id")
    private String tenantId;
    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 修改人
     */
    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    private String updateBy;
    /**
     * 是否被删除 0-否 1-是
     */
    @TableLogic
    @TableField(value = "iz_del",fill = FieldFill.INSERT )
    private Integer izDel;
}
