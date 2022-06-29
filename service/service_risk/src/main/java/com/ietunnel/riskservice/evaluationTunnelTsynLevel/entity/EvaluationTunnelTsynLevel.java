package com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationTunnelTsynLevel对象", description="隧道各区段突水涌泥风险等级打分表")
public class EvaluationTunnelTsynLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "区段id")
    private String sectionId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "突水涌泥可能性等级打分id（获取风险概率等级）")
    private String evaluationTunnelTsynPossibilityId;

    @ApiModelProperty(value = "风险等级标准id")
    private String riskRankCriteriaId;

    @ApiModelProperty(value = "风险损失等级（待填写）")
    private String riskLossLevel;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill= FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill=FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}