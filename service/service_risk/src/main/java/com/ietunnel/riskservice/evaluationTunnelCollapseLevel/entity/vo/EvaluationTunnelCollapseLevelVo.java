package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluationTunnelCollapseLevelVo {

    private String id;

    @ApiModelProperty(value = "隧道id（获取区段和隧道线路）")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    private String sectionId;

    @ApiModelProperty(value = "坍塌可能性等级打分id（获取风险概率等级）")
    private String evaluationTunnelCollapsePossibilityId;

    @ApiModelProperty(value = "风险等级标准id")
    private String riskRankCriteriaId;

    @ApiModelProperty(value = "隧道线路")
    private String tunnelLineName;

    @ApiModelProperty(value = "起止桩号")
    private String staction;

    @ApiModelProperty(value = "风险概率等级")
    private String riskPossibilityLevel;

    @ApiModelProperty(value = "风险等级")
    private String riskLevel;

    @ApiModelProperty(value = "风险损失等级（待打分）")
    private String riskLossLevel;

}