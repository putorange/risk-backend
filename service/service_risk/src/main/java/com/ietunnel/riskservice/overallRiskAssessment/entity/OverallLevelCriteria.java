package com.ietunnel.riskservice.overallRiskAssessment.entity;

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
 * 施工安全总体风险分级标准表
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="OverallLevelCriteria对象", description="施工安全总体风险分级标准表")
public class OverallLevelCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "施工安全总体风险分级标准id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private  String id;

    @ApiModelProperty(value = "施工安全总体风险分级名称")
    private String level;

    @ApiModelProperty(value = "计算分值")
    private String score;

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
