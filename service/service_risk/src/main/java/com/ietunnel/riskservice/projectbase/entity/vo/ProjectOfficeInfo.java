package com.ietunnel.riskservice.projectbase.entity.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @Auther: zxg
 * @Date:2022/01/07/17:30
 */
public class ProjectOfficeInfo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "项目办id")
    private String id;

    @ApiModelProperty(value = "关联项目管理公司id")
    private String managementId;

    @ApiModelProperty(value = "项目办名称")
    private String name;

    @ApiModelProperty(value = "项目造价")
    private String costs;

    @ApiModelProperty(value = "项目位置")
    private String location;

    @ApiModelProperty(value = "负责人")
    private String manager;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "项目计划开始时间")
    private Date startDay;

    @ApiModelProperty(value = "项目计划结束时间")
    private Date endDay;

    @ApiModelProperty(value = "工程规模")
    private String scale;

    @ApiModelProperty(value = "γ值")
    private Integer γValue;

}
