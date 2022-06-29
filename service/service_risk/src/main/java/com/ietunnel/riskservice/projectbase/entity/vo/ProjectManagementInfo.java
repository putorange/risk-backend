package com.ietunnel.riskservice.projectbase.entity.vo;

import com.ietunnel.riskservice.projectbase.entity.ProjectManagement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: zxg
 * @Date:2022/01/06/20:46
 */

@ApiModel(value = "项目管理公司基本信息", description = "编辑项目管理公司的表单对象")
public class ProjectManagementInfo {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "项目管理公司ID")
    private String id;

    @ApiModelProperty(value = "项目管理公司名称")
    private String name;
}
