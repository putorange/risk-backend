package com.ietunnel.riskservice.evaluationOverallRisk.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 总体风险评估指标体系打分表 Mapper 接口
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
public interface ExportMapper extends BaseMapper<EportEvalutionOverallRisk> {

    @Select("SELECT indicatorname,subindicatorname,classificationname,evaluationScore score\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT * from\n" +
            "\t(\n" +
            "\t\tSELECT indicatorname,subindicatorname,classificationname,indicatorid,subindicatorid,classificationid\n" +
            "\t\tFROM\n" +
            "\t\t(SELECT ori.`name` indicatorname,ori.id indicatorid,ors.`name` subindicatorname, ors.id subindicatorid,orc.id classificationid,orc.`name` classificationname\n" +
            "\t\tFROM overall_risk_indicator ori \n" +
            "\t\tLEFT JOIN overall_risk_subindicator ors ON ori.id = ors.indicator_id\n" +
            "\t\tLEFT JOIN overall_risk_classification orc ON ors.id = orc.subindicator_id\n" +
            "\t\tWHERE ori.is_deleted = 0 AND ors.is_deleted = 0 AND orc.is_deleted = 0 \n" +
            "\t\t)a\n" +
            "\t\tORDER BY a.indicatorid,a.subindicatorid,a.classificationid\n" +
            "\t)b\n" +
            "\tLEFT JOIN\n" +
            "\t(\n" +
            "\t\tSELECT e.overall_risk_classification_id overall_risk_classification_id, e.score  evaluationScore\n" +
            "\t\tfrom evaluation_overall_risk e\n" +
            "\t\twhere e.tunnel_id = #{tunnelID} and e.expert_id = #{expertId}\n" +
            "\t)c\n" +
            "\tON b.classificationid = c.overall_risk_classification_id\n" +
            ")d\n" +
            "ORDER BY d.indicatorid,d.subindicatorid,d.classificationid\n")
    List<EportEvalutionOverallRisk> selectALLList(String tunnelID,String expertId);

}
