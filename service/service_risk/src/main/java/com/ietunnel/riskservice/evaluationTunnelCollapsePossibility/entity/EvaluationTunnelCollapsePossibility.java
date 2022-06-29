package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity;

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
 * 隧道各区段坍塌事故可能性评估打分表
 * </p>
 *
 * @author wxb
 * @since 2022-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationTunnelCollapsePossibility对象", description="隧道各区段坍塌事故可能性评估打分表")
public class EvaluationTunnelCollapsePossibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "区段id，获取区段，对应的围岩级别")
    private String sectionId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "坍塌事故可能性等级标准id")
    private String collapsePossibilityRankCriteriaId;

    @ApiModelProperty(value = "P值")
    private Double pValue;

    @ApiModelProperty(value = "gama")
    private String gama;

    @ApiModelProperty(value = "断层破碎带（B）")
    private Integer faultFractureZone;

    @ApiModelProperty(value = "渗水状态（C）")
    private Double seepageState;

    @ApiModelProperty(value = "地质符合性（D）")
    private Integer geologyCompliance;

    @ApiModelProperty(value = "施工方法（E）")
    private Integer constructionMethod;

    @ApiModelProperty(value = "施工步距F（a）")
    private Double fA;

    @ApiModelProperty(value = "施工步距（b）")
    private Double fB;

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
