package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluationTunnelWsbzPossibilityVo implements Serializable {


    private String evaluationTunnelWsbzPossibilityId;

    @ApiModelProperty(value = "区段id，获取区段，对应的围岩级别")
    private String sectionId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "坍塌事故可能性等级标准id")
    private String wsbzPossibilityRankCriteriaId;

    private String tunnelLineName;

    private String staction;

    @ApiModelProperty(value = "P值")
    private String pvalue;

    @ApiModelProperty(value = "γ 值")
    private String gama;

    @ApiModelProperty(value = "瓦斯含量（A）")
    private Double gasContent;

    @ApiModelProperty(value = "洞内通风（B）")
    private Double caveVentilation;

    @ApiModelProperty(value = "机械防爆情况（C）")
    private Double machineExplosionProof;

    @ApiModelProperty(value = "瓦斯监测体系（D）")
    private Double gasMonitorSystem;

}
