package com.ietunnel.riskservice.projectbase.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 竖井围岩占比表 
 * </p>
 *
 * @author wxm
 * @since 2022-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ShaftRockProportion对象", description="竖井围岩占比表 ")
public class ShaftRockProportion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "竖井id")
    private String shaftId;

    @ApiModelProperty(value = "衬砌类型")
    private String liningType;

    @ApiModelProperty(value = "结构埋深")
    private Double structureDepth;

    @ApiModelProperty(value = "衬砌类型长度")
    private Double liningTypeLength;

    @ApiModelProperty(value = "所占比例 %")
    private Double proportion;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
