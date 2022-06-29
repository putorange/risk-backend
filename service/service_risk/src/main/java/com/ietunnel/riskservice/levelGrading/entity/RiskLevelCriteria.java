package com.ietunnel.riskservice.levelGrading.entity;

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
 * 风险等级标准表
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RiskLevelCriteria对象", description="风险等级标准表")
public class RiskLevelCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "可能等级num")
    private Integer possibleLevelNum;

    @ApiModelProperty(value = "可能等级名称")
    private String possibleLevelName;

    @ApiModelProperty(value = "损失等级num")
    private Integer lossLevelNum;

    @ApiModelProperty(value = "损失等级名称")
    private String lossLevelName;

    @ApiModelProperty(value = "风险等级num")
    private Integer riskLevelNum;

    @ApiModelProperty(value = "风险等级名称")
    private String riskLevelName;

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
