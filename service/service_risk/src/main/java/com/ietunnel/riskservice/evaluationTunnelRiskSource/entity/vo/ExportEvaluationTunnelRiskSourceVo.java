package com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper =  false)
@Accessors(chain =  true)
public class ExportEvaluationTunnelRiskSourceVo implements Serializable {

    @ApiModelProperty(value = "隧道分项工程")
    private String tunnelDivisionWork;

    @ApiModelProperty(value = "隧道单位作业内容（危险源）")
    private String tunnelUnitWorkContent;

    @ApiModelProperty(value = "风险事件")
    private String riskEvent;

    @ApiModelProperty(value = "受伤害人员类型（待填写）")
    private String hurtType;

    @ApiModelProperty(value = "伤害程度 (待填写)")
    private String hurtLevel;

    @ApiModelProperty(value = "不安全状态")
    private String unsafeState;

    @ApiModelProperty(value = "不安全行为")
    private String unsafeBehavior;

}