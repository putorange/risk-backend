package com.ietunnel.riskservice.evaluationTunnelRiskSource.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationRiskSourceTunnel对象", description="隧道风险源风险分析")
public class EvaluationRiskSourceTunnel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "隧道单位作业内容（危险源）id")
    private String tunnelUnitWorkContent;

    @ApiModelProperty(value = "隧道分项工程id")
    private String tunnelDivisionWork;

    @ApiModelProperty(value = "风险事件id")
    private String riskEvent;

    @ApiModelProperty(value = "不安全状态id")
    private String unsafeState;

    @ApiModelProperty(value = "不安全行为id")
    private String unsafeBehavior;

    @ApiModelProperty(value = "受伤害人员类型")
    private String hurtType;

    @ApiModelProperty(value = "伤害程度 (待填写)")
    private String hurtLevel;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date gmtModified;

}