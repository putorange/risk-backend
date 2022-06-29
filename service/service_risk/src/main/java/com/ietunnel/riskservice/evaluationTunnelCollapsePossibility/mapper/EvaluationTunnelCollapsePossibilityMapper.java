package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.mapper;

import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.EvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity.EvaluationTunnelCollapsePossibility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道各区段坍塌事故可能性评估打分表 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-06-09
 */
public interface EvaluationTunnelCollapsePossibilityMapper extends BaseMapper<EvaluationTunnelCollapsePossibility> {


//    @Select("SELECT a.*,e.gama gama\n" +
//            "FROM\n" +
//            "(\n" +
//            "SELECT segment_id segmentId,n.id sectionId,\n" +
//            "\t\t\tname ,tunnel_line_name tunnelLineName,\n" +
//            "\t\t\tCONCAT(start_station,'~',end_station) staction,n.rock_grade rockGrade\n" +
//            "FROM `segment` t, section n\n" +
//            "WHERE t.id = n.segment_id\n" +
//            ")a, evaluation_tunnel_safe_management e\n" +
//            "WHERE a.segmentId = e.segment_id\n" +
//            "ORDER BY tunnelLineName,staction")
    @Select("SELECT d.*,cp.event_probability eventProbability,cp.`level`\n" +
            "FROM\n" +
            "(\n" +
            "\tSELECT b.*,c.id evaluationTunnelCollapsePossibilityId,c.expert_id expertId,c.collapse_possibility_rank_criteria_id collapsePossibilityRankCriteriaId ,\n" +
            "\t\t\t\tc.fault_fracture_zone faultFractureZone, c.seepage_state seepageState, c.p_value pValue,\n" +
            "\t\t\t\tc.geology_compliance geologyCompliance, c.construction_method constructionMethod,\n" +
            "\t\t\t\tc.f_a fA ,c.f_b fB\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t\tSELECT a.*,e.gama gama\n" +
            "\t\tFROM\n" +
            "\t\t(\n" +
            "\t\t\tSELECT n.tunnel_id tunnelId,segment_id segmentId,n.id sectionId,\n" +
            "\t\t\t\t\t\tname ,tunnel_line_name tunnelLineName,\n" +
            "\t\t\t\t\t\tCONCAT(start_station,'~',end_station) staction,n.rock_grade rockGrade\n" +
            "\t\t\tFROM `segment` t, section n\n" +
            "\t\t\tWHERE t.id = n.segment_id and n.tunnel_id=#{tunelId} AND t.is_deleted = 0 AND n.is_deleted =0\n" +
            "\t\t)a, evaluation_tunnel_safe_management e\n" +
            "\t\tWHERE a.segmentId = e.segment_id AND e.is_deleted =0\n" +
            "\t)b\n" +
            "\tLEFT JOIN\n" +
            "\t(\n" +
            "\tSELECT *\n" +
            "\tFROM evaluation_tunnel_collapse_possibility et WHERE expert_id = #{expertId}\n" +
            "\t)c\n" +
            "\tON b.sectionId = c.section_id AND c.is_deleted =0\n" +
            ")d\n" +
            "LEFT JOIN collapse_possibility_rank_criteria cp\n" +
            "ON d.collapsePossibilityRankCriteriaId = cp.id\n" +
            "ORDER BY tunnelLineName,staction\n")
    List<EvaluationTunnelCollapsePossibilityVo> getList(String tunelId, String expertId);


    @Select("SELECT d.tunnelLineName,d.staction, d.rockGrade,d.faultFractureZone,d.seepageState,d.geologyCompliance,d.constructionMethod,d.fA,d.fB, d.gama,d.pValue ,cp.`level`,cp.event_probability eventProbability\n" +
            "FROM\n" +
            "(\n" +
            "\tSELECT b.*,c.id evaluationTunnelCollapsePossibilityId,c.expert_id expertId,c.collapse_possibility_rank_criteria_id collapsePossibilityRankCriteriaId ,\n" +
            "\t\t\t\tc.fault_fracture_zone faultFractureZone, c.seepage_state seepageState, c.p_value pValue,\n" +
            "\t\t\t\tc.geology_compliance geologyCompliance, c.construction_method constructionMethod,\n" +
            "\t\t\t\tc.f_a fA ,c.f_b fB\n" +
            "\tFROM\n" +
            "\t(\n" +
            "\t\tSELECT a.*,e.gama gama\n" +
            "\t\tFROM\n" +
            "\t\t(\n" +
            "\t\t\tSELECT n.tunnel_id tunnelId,segment_id segmentId,n.id sectionId,\n" +
            "\t\t\t\t\t\tname ,tunnel_line_name tunnelLineName,\n" +
            "\t\t\t\t\t\tCONCAT(start_station,'~',end_station) staction,n.rock_grade rockGrade\n" +
            "\t\t\tFROM `segment` t, section n\n" +
            "\t\t\tWHERE t.id = n.segment_id and n.tunnel_id=#{tunelId} AND t.is_deleted = 0 AND n.is_deleted =0\n" +
            "\t\t)a, evaluation_tunnel_safe_management e\n" +
            "\t\tWHERE a.segmentId = e.segment_id AND e.is_deleted =0\n" +
            "\t)b\n" +
            "\tLEFT JOIN\n" +
            "\t(\n" +
            "\tSELECT *\n" +
            "\tFROM evaluation_tunnel_collapse_possibility et WHERE expert_id = #{expertId}\n" +
            "\t)c\n" +
            "\tON b.sectionId = c.section_id AND c.is_deleted =0\n" +
            ")d\n" +
            "LEFT JOIN collapse_possibility_rank_criteria cp\n" +
            "ON d.collapsePossibilityRankCriteriaId = cp.id\n" +
            "ORDER BY tunnelLineName,staction\n")
    List<ExportEvaluationTunnelCollapsePossibilityVo> exportList(String tunelId,String expertId);
}
