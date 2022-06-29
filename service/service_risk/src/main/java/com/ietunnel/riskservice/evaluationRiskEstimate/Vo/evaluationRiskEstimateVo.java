package com.ietunnel.riskservice.evaluationRiskEstimate.Vo;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class evaluationRiskEstimateVo implements Serializable {

    //隧道风险源风险分析表id
    private String evaluationRiskSourceTunnelId;

    private String tunnelId;

    private String expertId;

    //危险源
    private String tunnelUnitWorkContent;

    //分项工程
    private String tunnelDivisionWork;

    //事故潜在类型
    private String riskEvent;

    private String oldhurtLevel;

    private String[] hurtLevel;

    private String id;

    private String lscore;

    private String escore;

    private String cscore;

    private String dscore;

    private String level;

    private String dangerDegree;

}
