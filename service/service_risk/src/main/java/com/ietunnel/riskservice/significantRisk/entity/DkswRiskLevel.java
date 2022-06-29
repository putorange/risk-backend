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
 * 人员伤亡等级标准
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="DkswRiskLevel对象", description="人员伤亡等级标准")
public class DkswRiskLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "洞口失稳风险等级表id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "风险事件洞口")
    private String risckNamePortal;

    @ApiModelProperty(value = "风险事件")
    private String risckName;

    @ApiModelProperty(value = "风险概率等级")
    private Long probabilityLevel;

    @ApiModelProperty(value = "风险损失等级")
    private Long loseLevel;

    @ApiModelProperty(value = "风险等级")
    private String level;

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
