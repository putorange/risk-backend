package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity;

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
 * 隧道各区段瓦斯爆炸可能性评估打分表
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationTunnelWsbzPossibility对象", description="隧道各区段瓦斯爆炸可能性评估打分表")
public class EvaluationTunnelWsbzPossibility implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "区段id，获取区段，对应的围岩级别")
    private String sectionId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "坍塌事故可能性等级标准id")
    private String wsbzPossibilityRankCriteriaId;

    @ApiModelProperty(value = "P值")
    private String pValue;

    @ApiModelProperty(value = "γ 值")
    private String gama;

    @ApiModelProperty(value = "瓦斯含量（A）")
    private Double gasContent;

    @ApiModelProperty(value = "洞内通风（B）")
    private Double caveVentilation;

    @ApiModelProperty(value = "机械防爆情况（C）")
    private Double machineExplosionProof;

    @ApiModelProperty(value = "瓦斯监测体系（D）")
    private Double gasMonitorSystem;

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
