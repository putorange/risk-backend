package com.ietunnel.riskservice.projectbase.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: zxg
 * @Date:2021/12/16/11:33
 */

@Data
public class IQuery {

    @ApiModelProperty(value = "名称")
    private String name;


    @ApiModelProperty(value = "隧道线路",example = "隧道左线")
    private String tunnelLineName;

    @ApiModelProperty(value = "围岩级别",example = "Ⅴ")
    // @TableField(value = "rockGrade")
    private Integer rockGrade;

    @ApiModelProperty(value = "围岩类别",example = "Ⅴ")
    // @TableField(value = "rockGrade")
    private Integer rockType;

    @ApiModelProperty(value = "竖井类型",example = "通风竖井")
    private String type;

    @ApiModelProperty(value = "衬砌类型")
    private String liningType;

    @ApiModelProperty(value = "项目管理公司id")
    private String managementId;

    @ApiModelProperty(value = "标段id")
    private String segmentId;


    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    private String begin;//注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;

}
