package com.ietunnel.riskservice.evaluationRiskEstimate.Vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class exportEvaluationRiskEstimateVo implements Serializable {

    //分项工程
    private String tunnelDivisionWork;

    //危险源
    private String tunnelUnitWorkContent;

    //事故潜在类型
    private String riskEvent;

    private String oldhurtLevel;

    private String lscore;

    private String escore;

    private String cscore;

    private String dscore;

    private String level;

    private String dangerDegree;

}
