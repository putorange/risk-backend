package com.ietunnel.riskservice.specialRisk.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: zxg
 * @Date:2022/04/13/0:07
 */
@Data
public class DivisionWork {

    @ApiModelProperty(value = "隧道分部工程id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道分部工程名称")
    private String name;

    // 多级分类:分部工程里有children，children为分项工程
    private List<SubDivisionWork> children = new ArrayList<>();
}
