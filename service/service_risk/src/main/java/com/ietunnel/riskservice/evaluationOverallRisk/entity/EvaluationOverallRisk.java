package com.ietunnel.riskservice.evaluationOverallRisk.entity;

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
 * 总体风险评估指标体系打分表
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationOverallRisk对象", description="总体风险评估指标体系打分表")
public class EvaluationOverallRisk implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "总体风险评估指标体系的分类id")
    private String overallRiskClassificationId;

    @ApiModelProperty(value = "打分分值")
    private String score;

    @ApiModelProperty(value = "标识 (隧道 | 竖井)")
    private Integer flag;

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
