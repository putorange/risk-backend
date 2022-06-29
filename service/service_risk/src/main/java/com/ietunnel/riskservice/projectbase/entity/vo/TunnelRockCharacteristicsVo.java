package com.ietunnel.riskservice.projectbase.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 　　* @param
 * 　　* @return
 * 　　* @author wxm
 * 　　* @date 2022-04-11
 *
 */
@Data
@EqualsAndHashCode(callSuper =  false)
@Accessors(chain =  true)
public class TunnelRockCharacteristicsVo implements Serializable {

    @ApiModelProperty(value = "起始区段")
    private String startStation;

    @ApiModelProperty(value = "终止区段")
    private String endStation;

    @ApiModelProperty(value = "隧道路线")
    private String tunnelLineName;

    @ApiModelProperty(value = "围岩级别")
    private String rockGrade;

    @ApiModelProperty(value = "[BQ]")
    private String basicQuality;

    @ApiModelProperty(value = "定性特征")
    private String qualitativeCharacteristics;

}
