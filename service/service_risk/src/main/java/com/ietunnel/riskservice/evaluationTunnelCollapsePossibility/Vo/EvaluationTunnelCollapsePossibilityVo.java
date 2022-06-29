package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class EvaluationTunnelCollapsePossibilityVo implements Serializable {

    //标段id
    public String segmentId;

    //区段id
    public String sectionId;

    //标段名称
    public String name;

    //隧道线路名称
    public String tunnelLineName;

    //区段起始终止
    public String staction;

    //围岩级别
    public String rockGrade;

    public String gama;

    public String evaluationTunnelCollapsePossibilityId;

    public String expertId;

    public String pValue;

    public String collapsePossibilityRankCriteriaId;

    public String faultFractureZone;

    public String seepageState;

    public String geologyCompliance;

    public String constructionMethod;

    public String fA;

    public String fB;

    public String eventProbability;

    public String level;

}
