package com.ietunnel.riskservice.projectbase.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 　　* @param
 * 　　* @return
 * 　　* @author wxm
 * 　　* @date 2022-04-19
 *
 */
@Data
@EqualsAndHashCode(callSuper =  false)
@Accessors(chain =  true)
public class TunnelRockProportionVo implements Serializable {

    @ApiModelProperty(value = "隧道围岩级别")
    private String rockGrade;

    @ApiModelProperty(value = "4、5级围岩所占比例 %")
    private Double proportion;



}
