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
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <p>
 * 隧道围岩表
 * </p>
 *
 * @author wxm
 * @since 2022-01-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TunnelRock对象", description="隧道围岩表")
public class TunnelRock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "隧道围岩id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "隧道id")
    private String tunnelId;

    @ApiModelProperty(value = "隧道线路名称(左|右线)")
    private String tunnelLineName;

    @ApiModelProperty(value = "隧道围岩级别num")
    private String rockGrade;

    @ApiModelProperty(value = "各级围岩长度")
    private Double length;

    @ApiModelProperty(value = "各级围岩所占比例")
    private Double proportion;

//    @ApiModelProperty(value = "[BQ]围岩基本质量指标")
//    private String basicQuality;
//
//    @ApiModelProperty(value = "定性特征")
//    private String qualitativeCharacteristics;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
