package com.ietunnel.riskservice.evaluationOverallRisk.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 总体风险评估指标体系打分表
 * </p>
 *
 * @author wxm
 * @since 2022-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EportEvalutionOverallRisk implements Serializable {

    private String indicatorname;

    private  String subindicatorname;

    private  String classificationname;

    private Integer score;

}
