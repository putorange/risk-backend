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
 * 事故发生可能性等级标准
 * </p>
 *
 * @author wxb
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EventPossibilityLevel对象", description="事故发生可能性等级标准")
public class EventPossibilityLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "概率范围")
    private String probabilityRange;

    @ApiModelProperty(value = "中心值                                                                                                                                                                                                                                                                                                                                                                              ")
    private Double centerValue;

    @ApiModelProperty(value = "概率等级")
    private String probabilityLevel;

    @ApiModelProperty(value = "概率等级描述")
    private String probabilityLevelDescription;

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
