package com.ietunnel.riskservice.projectbase.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 竖井围岩情况表
 * </p>
 *
 * @author wxm
 * @since 2022-01-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShaftRockInfo对象", description="竖井围岩情况表")
public class ShaftRockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "竖井id")
    private String shaftId;

    @ApiModelProperty(value = "施工区段_1（深度起点值）")
    @TableField("startDepth")
    private Double startDepth;

    @ApiModelProperty(value = "施工区段_2（深度终点值）")
    @TableField("endDepth")
    private Double endDepth;

    @ApiModelProperty(value = "围岩类别")
    private String rockType;

    @ApiModelProperty(value = "岩性级风化程度")
    private String lithologyWeathering;

    @ApiModelProperty(value = "节理发育程度")
    private String jointDevelopmentDegree;

    @ApiModelProperty(value = "结构特征")
    private String structureCharacteristics;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
