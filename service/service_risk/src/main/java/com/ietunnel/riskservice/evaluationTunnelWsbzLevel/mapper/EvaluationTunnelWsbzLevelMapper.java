package com.ietunnel.riskservice.evaluationTunnelWsbzLevel.mapper;

import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.EvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.ExportEvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.EvaluationTunnelWsbzLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸风险等级打分表 Mapper 接口
 * </p>
 *
 * @author wxm
 * @since 2022-06-25
 */
public interface EvaluationTunnelWsbzLevelMapper extends BaseMapper<EvaluationTunnelWsbzLevel> {

	@Select("SELECT c.*,rlc.risk_level_name riskLevel\n" +
			"FROM\n" +
			"(\n" +
			"SELECT a.*,b.id evaluationTunnelWsbzLevelId,b.expert_id expertId,b.risk_loss_level riskLossLevel,b.risk_rank_criteria_id riskRankCriteriaId\n" +
			"FROM\n" +
			"(SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,etwp.id evaluationTunnelWsbzPossibilityId,cprc.level riskPossibilityLevel\n" +
			"FROM evaluation_tunnel_wsbz_possibility etwp,section s,wsbz_possibility_rank_criteria cprc\n" +
			"WHERE etwp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = etwp.wsbz_possibility_rank_criteria_id AND etwp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0 )a\t\t\n" +
			"LEFT JOIN (SELECT * FROM evaluation_tunnel_wsbz_level etwl)b\n" +
			"ON b.expert_id = #{expertId} AND a.sectionId= b.section_id AND b.evaluation_tunnel_wsbz_possibility_id = a.evaluationTunnelWsbzPossibilityId)c\t\t\t\n" +
			"LEFT JOIN risk_level_criteria rlc \n" +
			"ON c.riskRankCriteriaId = rlc.id \t\t\n" +
			"ORDER BY tunnelLineName ,staction")
	List<EvaluationTunnelWsbzLevelVo> getList(String tunnelId,String expertId);


	@Select("SELECT @rownum:=@rownum+1 AS orderNum  ,tunnelLineName,b.staction,b.riskPossibilityLevel,b.riskLossLevel,rlc.risk_level_name riskLevel\n" +
			"FROM (select @rownum := 0) as rownum,\n" +
			"(SELECT a.*,etwl.risk_rank_criteria_id riskRankCriteriaId,etwl.risk_loss_level riskLossLevel FROM\n" +
			"(\n" +
			"SELECT s.tunnel_id tunnelId, s.segment_id segmentId,s.id sectionId ,s.tunnel_line_name tunnelLineName,CONCAT(start_station,'~',end_station) staction,etcp.id evaluationTunnelWsbzPossibilityId, cprc.level riskPossibilityLevel\n" +
			"FROM evaluation_tunnel_wsbz_possibility etcp,section s,wsbz_possibility_rank_criteria cprc\n" +
			"WHERE etcp.section_id = s.id AND s.tunnel_id = #{tunnelId} AND cprc.id = etcp.wsbz_possibility_rank_criteria_id AND etcp.is_deleted = 0 AND s.is_deleted = 0 AND cprc.is_deleted = 0\n" +
			")a\n" +
			"LEFT JOIN \n" +
			"evaluation_tunnel_wsbz_level etwl\n" +
			"ON \n" +
			"etwl.expert_id = #{expertId} AND \n" +
			"etwl.section_id = a.sectionId AND etwl.is_deleted = 0)b\n" +
			"LEFT JOIN risk_level_criteria rlc\n" +
			"ON b.riskRankCriteriaId = rlc.id\n" +
			"ORDER BY tunnelLineName,staction")
	List<ExportEvaluationTunnelWsbzLevelVo> exportList(String tunnelId,String expertId);

}
