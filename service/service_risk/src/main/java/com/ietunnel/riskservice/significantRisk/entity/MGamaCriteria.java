package com.ietunnel.riskservice.significantRisk.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评估指标与折减系数对照表
 * </p>
 *
 * @author wxb
 * @since 2022-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="MGamaCriteria对象", description="评估指标与折减系数对照表")
public class MGamaCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "m_γ id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "计算分值M1")
    private Integer m1;

    @ApiModelProperty(value = "计算分值M2")
    private Integer m2;

    @ApiModelProperty(value = "折减系数")
    private Double value;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;


}
