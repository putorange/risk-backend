package com.ietunnel.riskservice.projectbase.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper =  false)
@Accessors(chain =  true)
public class ExportTunnelRockCharacteristicsVo implements Serializable {

    @ApiModelProperty(value = "起止区段")
    private String staction;

    @ApiModelProperty(value = "隧道路线")
    private String tunnelLineName;

    @ApiModelProperty(value = "围岩级别")
    private String rockGrade;

    @ApiModelProperty(value = "[BQ]")
    private String basicQuality;

    @ApiModelProperty(value = "定性特征")
    private String qualitativeCharacteristics;

}