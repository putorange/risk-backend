package com.ietunnel.riskservice.evaluationManagement.entity;

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
 * 隧道安全管理评估指标打分表
 * </p>
 *
 * @author cmy
 * @since 2022-05-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationTunnelSafeManagement对象", description="隧道安全管理评估指标打分表")
public class EvaluationTunnelSafeManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "标段id")
    private String segmentId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "隧道安全管理评估指标id")
    private String safeManagementIndicatorId;

    @ApiModelProperty(value = "隧道安全管理评估指标分类id")
    private String safeManagementClassificationId;

    @ApiModelProperty(value = "评估分值")
    private Integer score;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}

