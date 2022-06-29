package com.ietunnel.riskservice.specialRisk.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: zxg
 * @Date:2022/04/13/0:18
 */
@Data
public class WorkEventContent {
    @ApiModelProperty(value = "隧道单位作业id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道单位作业名称")
    private String name;
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
}
