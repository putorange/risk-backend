package com.ietunnel.riskservice.projectbase.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 项目办
 * </p>
 *
 * @author zxg
 * @since 2021-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ProjectOffice对象", description="项目办")
public class ProjectOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "关联项目管理公司id")
    @TableId(value = "id", type = IdType.INPUT)
    private String managementId;

    @ApiModelProperty(value = "项目办名称")
    private String name;

    @ApiModelProperty(value = "项目造价")
    private String costs;

    @ApiModelProperty(value = "项目位置")
    private String location;

    @ApiModelProperty(value = "负责人")
    private String manager;

    @ApiModelProperty(value = "l联系电话")
    private String phone;

    @ApiModelProperty(value = "项目计划开始时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date startDay;

    @ApiModelProperty(value = "项目计划结束时间")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date endDay;

    @ApiModelProperty(value = "工程规模")
    private String scale;

    @ApiModelProperty(value = "γ值")
    private Integer γValue;

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
