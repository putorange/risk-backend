package com.ietunnel.riskservice.evaluationRiskEstimate.entity;

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
 * 风险估测汇总表
 * </p>
 *
 * @author wxb
 * @since 2022-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationRiskEstimate对象", description="风险估测汇总表")
public class EvaluationRiskEstimate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "风险估测汇总表ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "隧道风险源风险分析表id")
    private String evaluationRiskSourceTunnelId;

    @ApiModelProperty(value = "伤害程度")
    @TableField("hurt_level")
    private String hurtLevel;

    @ApiModelProperty(value = "风险估测（L）")
    @TableField("L_score")
    private String lScore;

    @ApiModelProperty(value = "风险估测（E） ")
    @TableField("E_score")
    private String eScore;

    @ApiModelProperty(value = "风险估测（C）")
    @TableField("C_score")
    private String cScore;

    @ApiModelProperty(value = "风险估测（D）")
    @TableField("D_score")
    private String dScore;

    @ApiModelProperty(value = "风险级别")
    @TableField("level")
    private String level;

    @ApiModelProperty(value = "dangerDegree")
    @TableField("danger_degree")
    private String dangerDegree;

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
