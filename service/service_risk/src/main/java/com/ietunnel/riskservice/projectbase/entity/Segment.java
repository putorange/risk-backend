package com.ietunnel.riskservice.projectbase.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 标段
 * </p>
 *
 * @author zxg
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="Segment对象", description="标段")
public class Segment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "标段id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "项目办id")
    @TableId(value = "id", type = IdType.INPUT)
    private String officeId;

    @ApiModelProperty(value = "标段名称")
    private String name;

    @ApiModelProperty(value = "标段简介")
    private String description;

    @ApiModelProperty(value = "左线起点里程")
    private String leftStartMileage;

    @ApiModelProperty(value = "左线终点里程")
    private String leftEndMileage;

    @ApiModelProperty(value = "左线长度")
    private double leftLength;

    @ApiModelProperty(value = "右线起点里程")
    private String rightStartMileage;

    @ApiModelProperty(value = "右线终点里程")
    private String rightEndMileage;

    @ApiModelProperty(value = "右线总长度")
    private double rightLength;

    @ApiModelProperty(value = "标段计划开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDay;

    @ApiModelProperty(value = "标段计划结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDay;

    @ApiModelProperty(value = "标段状态 1 （已开启）， 0（未开启）")
    private Integer isStart;

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
