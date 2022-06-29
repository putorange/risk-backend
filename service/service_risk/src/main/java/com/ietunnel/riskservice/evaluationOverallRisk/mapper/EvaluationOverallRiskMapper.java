package com.ietunnel.riskservice.evaluationOverallRisk.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationOverallRisk.entity.EvaluationOverallRisk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * 总体风险评估指标体系打分表 Mapper 接口
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
public interface EvaluationOverallRiskMapper extends BaseMapper<EvaluationOverallRisk> {

    @Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, s.indicator_id, s.description subindicatordescription, c.id classificationId, c.name classificationname, c.score classificationscore FROM overall_risk_indicator i,overall_risk_subindicator s, overall_risk_classification c  WHERE i.id=s.indicator_id and s.id=c.subindicator_id and i.is_deleted=0")
    List<EvaluationOverallRiskVo> selectMajorList(Page page);

    //@Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, s.indicator_id, s.description subindicatordescription, c.id classificationId, c.name classificationname, c.score classificationscore FROM overall_risk_indicator i,overall_risk_subindicator s, overall_risk_classification c  WHERE i.id=s.indicator_id and s.id=c.subindicator_id and i.is_deleted=0")
    @Select("SELECT i.name indicatorname, i.is_deleted isDeleted ,s.id subindicatorId, s.name subindicatorname, \n" +
            "       s.indicator_id, s.description subindicatordescription, c.id overallRiskClassificationId, \n" +
            "       c.name  classificationname, c.score classificationscore \n" +
            "FROM overall_risk_indicator i                         \n" +
            "LEFT JOIN overall_risk_subindicator s ON i.id=s.indicator_id\n" +
            "LEFT JOIN overall_risk_classification c ON s.id=c.subindicator_id\n" +
            "WHERE  i.is_deleted=0 and s.is_deleted=0 and c.is_deleted=0 \n" +
            "ORDER BY i.id,s.id,c.id")
    List<EvaluationOverallRiskVo> selectALLList();


    @Select("SELECT indicatorname,indicatorId,subindicatorname,subindicatorId,classificationname,overallRiskClassificationId,subindicatordescription,classificationscore,evaluationId,tunnelId,evaluationScore\n" +
            "FROM \n" +
            "(\n" +
            "\tSELECT * from\n" +
            "\t(\n" +
            "\t\tSELECT indicatorname,subindicatorname,classificationname,indicatorId,subindicatorId,overallRiskClassificationId,subindicatordescription ,classificationscore\n" +
            "\t\tFROM\n" +
            "\t\t(SELECT ori.`name` indicatorname,ori.id indicatorId,ors.`name` subindicatorname, ors.id subindicatorId,ors.description subindicatordescription,\n" +
            "\t\t\t\t\t\torc.id overallRiskClassificationId,orc.`name` classificationname, orc.score classificationscore\n" +
            "\t\tFROM overall_risk_indicator ori \n" +
            "\t\tLEFT JOIN overall_risk_subindicator ors ON ori.id = ors.indicator_id\n" +
            "\t\tLEFT JOIN overall_risk_classification orc ON ors.id = orc.subindicator_id\n" +
            "\t\tWHERE ori.is_deleted = 0 AND ors.is_deleted = 0 AND orc.is_deleted = 0 \n" +
            "\t\t)a\n" +
            "\t)b\n" +
            "\tLEFT JOIN\n" +
            "\t(\n" +
            "\t\tSELECT e.id evaluationId,e.tunnel_id tunnelId, e.overall_risk_classification_id overall_risk_classification_id, e.score  evaluationScore\n" +
            "\t\tfrom evaluation_overall_risk e\n" +
            "\t\twhere e.tunnel_id =#{tunnelid} and e.expert_id = #{expertId}\n" +
            "\t)c\n" +
            "\tON b.overallRiskClassificationId = c.overall_risk_classification_Id\n" +
            ")d\n" +
            "ORDER BY d.indicatorId,d.subindicatorId,d.overallRiskClassificationId")
    List<EvaluationOverallRiskVo> selectALLListBytunnelid(String tunnelid,String expertId);


    @Update("        UPDATE evaluation_overall_risk set expert_id =#{expertId},tunnel_id =#{tunnelId},overall_risk_classification_id=#{overallRiskClassificationId},flag =#{flag},score =#{score}\n" +
            "        WHERE id = #{id}")
    int updateByeId(EvaluationOverallRisk evaluationOverallRisk);


    @Delete("delete from evaluation_overall_risk where id= #{evaluationId}")
    int delId(String evaluationId);
}
