package com.ietunnel.riskservice.shaft.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 竖井表
 * </p>
 *
 * @author wxm
 * @since 2022-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Shaft对象", description="竖井表")
public class Shaft implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竖井id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "竖井类型 (通风 | 施工)")
    private String type;

    @ApiModelProperty(value = "长度")
    private Double length;

    @ApiModelProperty(value = "四、五级围岩所占比例")
    private String proportionGrade45;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
