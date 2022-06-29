package com.ietunnel.riskservice.significantRisk.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RiskResourceCheckTable {
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelid;

    @ApiModelProperty(value = "专家id")
    private String expertid;

    @ApiModelProperty(value = "风险源id")
    private String sourceid;

    @ApiModelProperty(value = "风险源名称")
    private String sourcename;

    @ApiModelProperty(value = "风险事故id")
    private String contentid;

    @ApiModelProperty(value = "风险事故名称")
    private String contentname;

    @ApiModelProperty(value = "坍塌（勾选状态）")
    private String collapse;

    @ApiModelProperty(value = "洞口失稳（勾选状态）")
    @TableField("holeInstability")
    private String holeinstability;

    @ApiModelProperty(value = "突水涌泥（勾选状态）")
    @TableField("waterInrush")
    private String waterinrush;
}
