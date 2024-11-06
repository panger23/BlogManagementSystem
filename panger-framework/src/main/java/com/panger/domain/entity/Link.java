package com.panger.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 友链
 * </p>
 *
 * @author panger
 * @since 2024-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sg_link")
@ApiModel(value="Link对象", description="友链")
public class Link implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("logo")
    private String logo;

    @TableField("description")
    private String description;

    @ApiModelProperty(value = "网站地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)")
    @TableField("status")
    private String status;

    @TableField("create_by")
    private Long createBy;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_by")
    private Long updateBy;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    @TableField("del_flag")
    private Integer delFlag;


}
