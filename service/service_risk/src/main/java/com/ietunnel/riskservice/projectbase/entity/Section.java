package com.ietunnel.riskservice.projectbase.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 区段
 * </p>
 *
 * @author zxg
 * @since 2021-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Section对象", description="区段")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区段id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "标段id")
    private String segmentId;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "隧道线(左、右线)")
    private String tunnelLineName;

    @ApiModelProperty(value = "起始桩号")
    private String startStation;

    @ApiModelProperty(value = "终点桩号")
    private String endStation;

    @ApiModelProperty(value = "围岩级别")
    private String rockGrade;

    @ApiModelProperty(value = "[BQ]围岩基本质量指标")
    private String basicQuality;

    @ApiModelProperty(value = "定性特征")
    private String qualitativeCharacteristics;

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
