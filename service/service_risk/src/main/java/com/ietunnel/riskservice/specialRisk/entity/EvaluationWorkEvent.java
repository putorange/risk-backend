package com.ietunnel.riskservice.specialRisk.entity;

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
 * 隧道施工作业活动与典型事故类型
 * </p>
 *
 * @author zxg
 * @since 2022-04-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EvaluationWorkEvent对象", description="隧道施工作业活动与典型事故类型")
public class EvaluationWorkEvent implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    private String name;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "专家id")
    private String expertId;

    @ApiModelProperty(value = "单位作业id")
    private String unitId;

    @ApiModelProperty(value = "分项工程id")
    private String subdivisionId;

    @ApiModelProperty(value = "物体打击（勾选状态）")
    private String hit;

    @ApiModelProperty(value = "高出坠落（勾选状态）")
    private String fall;

    @ApiModelProperty(value = "触电（勾选状态）")
    private String electricity;

    @ApiModelProperty(value = "起重伤害（勾选状态）")
    private String lift;

    @ApiModelProperty(value = "爆炸（勾选状态）")
    private String explore;

    @ApiModelProperty(value = "冒顶片帮（勾选状态）")
    private String roof;

    @ApiModelProperty(value = "突水涌泥（勾选状态）")
    private String water;

    @ApiModelProperty(value = "放炮（勾选状态）")
    private String blast;

    @ApiModelProperty(value = "火灾（勾选状态）")
    private String fire;

    @ApiModelProperty(value = "机械伤害（勾选状态）")
    private String mechanical;

    @ApiModelProperty(value = "车辆伤害（勾选状态）")
    private String vehicle;

    @ApiModelProperty(value = "倒塌")
    private String collapse;

    @ApiModelProperty(value = "其他")
    private String others;

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
