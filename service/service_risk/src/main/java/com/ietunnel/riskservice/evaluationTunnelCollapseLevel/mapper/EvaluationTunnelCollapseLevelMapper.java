package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.mapper;


import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.EvaluationTunnelCollapseLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.EvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.ExportEvaluationTunnelCollapseLevelVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EvaluationTunnelCollapseLevelMapper extends BaseMapper<EvaluationTunnelCollapseLevel> {

    @Select("SELECT c.*,rlc.risk_level_name riskLevel\n" +
            "FROM\n" +
            "(\n" +
            "SELECT a.*,b.id evaluationTunnelCollapseLevelId,b.expert_id expertId,b.risk_loss_level riskLossLevel,b.risk_rank_criteria_id riskRankCriteriaId\n" +
            "FROM\n" +
            "(SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,etcp.id evaluationTunnelCollapsePossibilityId,cprc.level riskPossibilityLevel\n" +
            "\t\t\tFROM evaluation_tunnel_collapse_possibility etcp,section s,collapse_possibility_rank_criteria cprc\n" +
            "\t\t\tWHERE etcp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = etcp.collapse_possibility_rank_criteria_id AND etcp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0 )a\t\t\n" +
            "\t\t\tLEFT JOIN (SELECT * FROM evaluation_tunnel_collapse_level etcl)b\n" +
            "\t\t\tON a.sectionId= b.section_id AND b.evaluation_tunnel_collapse_possibility_id = a.evaluationTunnelCollapsePossibilityId)c\t\t\t\n" +
            "\t\t\tLEFT JOIN risk_level_criteria rlc \n" +
            "\t\t\tON c.riskRankCriteriaId = rlc.id \t\t\n" +
            "\t\t\tORDER BY tunnelLineName ,staction")
    List<EvaluationTunnelCollapseLevelVo> getList(String tunnelId);


    @Select("SELECT @rownum:=@rownum+1 AS orderNum ,tunnelLineName,b.staction,b.riskPossibilityLevel,b.riskLossLevel,rlc.risk_level_name riskLevel\n" +
            "FROM (select @rownum := 0) as rownum, (\n" +
            "SELECT a.*,etcl.risk_rank_criteria_id riskRankCriteriaId,etcl.risk_loss_level riskLossLevel FROM\n" +
            "(\n" +
            "SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,etcp.id evaluationTunnelCollapsePossibilityId, cprc.level riskPossibilityLevel\n" +
            "FROM evaluation_tunnel_collapse_possibility etcp,section s,collapse_possibility_rank_criteria cprc\n" +
            "WHERE etcp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = etcp.collapse_possibility_rank_criteria_id AND etcp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0\n" +
            ")a\n" +
            "LEFT JOIN \n" +
            "evaluation_tunnel_collapse_level etcl\n" +
            "ON etcl.section_id = a.sectionId AND etcl.is_deleted = 0)b\n" +
            "\n" +
            "LEFT JOIN risk_level_criteria rlc\n" +
            "ON b.riskRankCriteriaId = rlc.id\n" +
            "ORDER BY tunnelLineName,staction")
    List<ExportEvaluationTunnelCollapseLevelVo> exportList(String tunnelId);

}