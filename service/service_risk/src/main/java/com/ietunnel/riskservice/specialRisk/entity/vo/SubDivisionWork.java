package com.ietunnel.riskservice.specialRisk.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zxg
 * @Date:2022/04/13/0:18
 */
@Data
public class SubDivisionWork {

    @ApiModelProperty(value = "隧道分项工程id")
    private String id;

    @ApiModelProperty(value = "隧道分项工程名称")
    private String name;

    // 多级分类:分项工程里有children，children为单位作业
    private List<WorkEventContent> children = new ArrayList<>();

}
