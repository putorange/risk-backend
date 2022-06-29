package com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ExportEvaluationTunnelTsynLevelVo {

    private String orderNum;

    @ApiModelProperty(value = "隧道线路")
    private String tunnelLineName;

    @ApiModelProperty(value = "起止桩号")
    private String staction;

    @ApiModelProperty(value = "风险概率等级")
    private String riskPossibilityLevel;

    @ApiModelProperty(value = "风险损失等级（待打分）")
    private String riskLossLevel;

    @ApiModelProperty(value = "风险等级")
    private String riskLevel;

}