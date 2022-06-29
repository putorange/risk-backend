package com.ietunnel.riskservice.significantRisk.entity;

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
 * 隧道风险源检查表
 * </p>
 *
 * @author zxg
 * @since 2022-06-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RiskResourceCheck对象", description="隧道风险源检查表")
public class RiskResourceCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "风险源id")
    private String significantRiskSourceId;

    @ApiModelProperty(value = "风险源名称")
    private String significantRiskSourceName;

    @ApiModelProperty(value = "风险事故id")
    private String significantRiskContentId;

    @ApiModelProperty(value = "风险事故名称")
    private String significantRiskContentName;

    @ApiModelProperty(value = "坍塌（勾选状态）")
    private String collapse;

    @ApiModelProperty(value = "洞口失稳（勾选状态）")
    @TableField("holeInstability")
    private String holeInstability;

    @ApiModelProperty(value = "突水涌泥（勾选状态）")
    @TableField("waterInrush")
    private String waterInrush;

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
