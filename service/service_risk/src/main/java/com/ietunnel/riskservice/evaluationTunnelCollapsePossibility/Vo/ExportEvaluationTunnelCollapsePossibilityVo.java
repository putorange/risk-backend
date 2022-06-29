package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class ExportEvaluationTunnelCollapsePossibilityVo implements Serializable {

    //隧道线路名称
    public String tunnelLineName;

    //区段起始终止
    public String staction;

    //围岩级别
    public String rockGrade;

    public String gama;

    public String pvalue;

    public String faultFractureZone;

    public String seepageState;

    public String geologyCompliance;

    public String constructionMethod;

    public String fa;

    public String fb;

    public String eventProbability;

    public String level;

}
