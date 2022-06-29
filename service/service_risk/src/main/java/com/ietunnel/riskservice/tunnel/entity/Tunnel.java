package com.ietunnel.riskservice.tunnel.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 隧道表
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tunnel对象", description="隧道表")
public class Tunnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "隧道id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道唯一标识")
    private String tunnelNum;

    @ApiModelProperty(value = "标段id")
    @TableId(value = "id", type = IdType.INPUT)

    private String segmentId;

    @ApiModelProperty(value = "隧道名称")
    private String name;

    @ApiModelProperty(value = "类型（公路|隧道|桥梁）字段暂保留")
    private String type;

    @ApiModelProperty(value = "总长度")
    private Double length;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
