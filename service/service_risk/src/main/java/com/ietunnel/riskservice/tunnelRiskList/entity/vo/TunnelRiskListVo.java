package com.ietunnel.riskservice.tunnelRiskList.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 　　* @param
 * 　　* @return
 * 　　* @author cmy
 * 　　* @date 2022-05-27
 *
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TunnelRiskList", description="隧道施工安全风险源普查清单")
public class TunnelRiskListVo implements Serializable {

    @ApiModelProperty(value = "风险源id")
    private String id;

    @ApiModelProperty(value = "风险源名称")
    private String name;

    @ApiModelProperty(value = "判断依据")
    private String potentialEvent;




}

