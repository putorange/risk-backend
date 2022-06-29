package com.ietunnel.riskservice.evaluationTunnelTsynLevel.mapper;

import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.EvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.ExportEvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.EvaluationTunnelTsynLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EvaluationTunnelTsynLevelMapper extends BaseMapper<EvaluationTunnelTsynLevel> {

    @Select("SELECT c.*,rlc.risk_level_name riskLevel\n" +
            "FROM\n" +
            "(\n" +
            "SELECT a.*,b.id evaluationTunneTsnyLevelId,b.expert_id expertId,b.risk_loss_level riskLossLevel,b.risk_rank_criteria_id riskRankCriteriaId\n" +
            "FROM\n" +
            "(SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,ettp.id evaluationTunnelTsynPossibilityId,cprc.level riskPossibilityLevel\n" +
            "\t\t\tFROM evaluation_tunnel_tsyn_possibility ettp,section s,collapse_possibility_rank_criteria cprc\n" +
            "\t\t\tWHERE ettp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = ettp.tsyn_possibility_rank_criteria_id AND ettp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0 )a\t\t\n" +
            "\t\t\tLEFT JOIN (SELECT * FROM evaluation_tunnel_tsyn_level ettl)b\n" +
            "\t\t\tON a.sectionId= b.section_id AND b.evaluation_tunnel_tsyn_possibility_id = a.evaluationTunnelTsynPossibilityId)c\t\t\t\n" +
            "\t\t\tLEFT JOIN risk_level_criteria rlc \n" +
            "\t\t\tON c.riskRankCriteriaId = rlc.id \t\t\n" +
            "\t\t\tORDER BY tunnelLineName ,staction")
    List<EvaluationTunnelTsynLevelVo> getList(String tunnelId);


    @Select("SELECT@rownum:=@rownum+1 AS orderNum ,c.tunnelLineName,c.staction,c.riskPossibilityLevel,c.riskLossLevel,rlc.risk_level_name riskLevel\n" +
            "FROM (select @rownum := 0) as rownum, \n" +
            "(\n" +
            "SELECT a.*,b.id evaluationTunneTsnyLevelId,b.expert_id expertId,b.risk_loss_level riskLossLevel,b.risk_rank_criteria_id riskRankCriteriaId\n" +
            "FROM\n" +
            "(SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,ettp.id evaluationTunnelTsynPossibilityId,cprc.level riskPossibilityLevel\n" +
            "\t\t\tFROM evaluation_tunnel_tsyn_possibility ettp,section s,collapse_possibility_rank_criteria cprc\n" +
            "\t\t\tWHERE ettp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = ettp.tsyn_possibility_rank_criteria_id AND ettp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0 )a\t\t\n" +
            "\t\t\tLEFT JOIN (SELECT * FROM evaluation_tunnel_tsyn_level ettl)b\n" +
            "\t\t\tON a.sectionId= b.section_id AND b.evaluation_tunnel_tsyn_possibility_id = a.evaluationTunnelTsynPossibilityId)c\t\t\t\n" +
            "\t\t\tLEFT JOIN risk_level_criteria rlc \n" +
            "\t\t\tON c.riskRankCriteriaId = rlc.id \t\t\n" +
            "\t\t\tORDER BY tunnelLineName ,staction\n")
    List<ExportEvaluationTunnelTsynLevelVo> exportList(String tunnelId);
}