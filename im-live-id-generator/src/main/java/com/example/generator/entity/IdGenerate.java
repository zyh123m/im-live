package com.example.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName t_id_generate_config
 */
@TableName(value ="t_id_generate_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdGenerate implements Serializable {
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 描述
     */
    private String remark;

    /**
     * 当前id所在阶段的闻值
     */
    private Long nextThreshold;

    /**
     * 初始化值
     */
    private Long initNum;

    /**
     * 当前id所在阶段的开始值
     */
    private Long currentStart;

    /**
     * id递增区间
     */
    private Integer step;

    /**
     * 是否有序 (0无序，1有序)
     */
    private Integer isSeq;

    /**
     * 业务前缀码如果没有则返回时不携带
     */
    private String idPrefix;

    /**
     * 乐观锁版本号
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}