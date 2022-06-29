package com.ietunnel.riskservice.evaluationOverallRisk.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 总体风险评估指标体系打分表
 * </p>
 *
 * @author wxm
 * @since 2022-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluationOverallRiskVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "总体风险评估指标表体系评估指标id")
    private String indicatorId;

    @ApiModelProperty(value = "总体风险评估指标表体系评估指标名称")
    private String indicatorname;

    @ApiModelProperty(value = "总体风险子评估指标体系子评估指标id")
    private String subindicatorId;

    @ApiModelProperty(value = "总体风险子评估指标体系子评估指标mingc")
    private String subindicatorname;

    @ApiModelProperty(value = "说明")
    private String subindicatordescription;

    @ApiModelProperty(value = "总体风险评估指标体系的分类id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String overallRiskClassificationId;

    @ApiModelProperty(value = "总体风险评估指标体系的分类名称")
    private String classificationname;

    @ApiModelProperty(value = "标准分值")
    private String classificationscore;

    @ApiModelProperty(value = "tunnelId")
    private String tunnelId;

    @ApiModelProperty(value = "ping gu id")
    private String evaluationId;

    @ApiModelProperty(value = "score")
    private String evaluationscore;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;
}
