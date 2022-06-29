package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.mapper;

import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.EvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.ExportEvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.EvaluationTunnelWsbzPossibility;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸可能性评估打分表 Mapper 接口
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
public interface EvaluationTunnelWsbzPossibilityMapper extends BaseMapper<EvaluationTunnelWsbzPossibility> {

	@Select("SELECT d.*,wprc.event_probability eventProbability,wprc.`level`\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT b.*,c.id evaluationTunnelWsbzPossibilityId,c.expert_id expertId,c.wsbz_possibility_rank_criteria_id wsbzPossibilityRankCriteriaId ,\n" +
			"\t\t\tc.gas_content gasContent, c.cave_ventilation caveVentilation, c.p_value pValue,\n" +
			"\t\t\tc.machine_explosion_proof machineExplosionProof, c.gas_monitor_system gasMonitorSystem\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT a.*,e.gama gama\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT n.tunnel_id tunnelId,segment_id segmentId,n.id sectionId,\n" +
			"\t\t\tt.name segmentName,tunnel_line_name tunnelLineName,\n" +
			"\t\t\tCONCAT(start_station,'~',end_station) staction,n.rock_grade rockGrade\n" +
			"\t\t\tFROM `segment` t, section n\n" +
			"\t\t\tWHERE t.id = n.segment_id and n.tunnel_id=#{tunnelId} AND t.is_deleted = 0 AND n.is_deleted =0\n" +
			"\t\t\t)a, evaluation_tunnel_safe_management e\n" +
			"\t\t\tWHERE a.segmentId = e.segment_id AND e.is_deleted =0\n" +
			"\t\t\t)b\n" +
			"\t\t\tLEFT JOIN\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT *\n" +
			"\t\t\tFROM evaluation_tunnel_wsbz_possibility et\n" +
			"\t\t\tWHERE expert_id = #{expertId}\n" +
			"\t\t\t)c\n" +
			"\t\t\tON b.sectionId = c.section_id AND c.is_deleted =0\n" +
			"\t\t\t)d\n" +
			"\t\t\tLEFT JOIN wsbz_possibility_rank_criteria wprc\n" +
			"\t\t\tON d.wsbzPossibilityRankCriteriaId = wprc.id\n" +
			"\t\t\tORDER BY tunnelLineName,staction")
	List<EvaluationTunnelWsbzPossibilityVo> getList(String tunnelId, String expertId);


	@Select("SELECT d.tunnelLineName,d.staction,d.gasContent, d.caveVentilation,\n" +
			"\t\t\td.machineExplosionProof, d.gasMonitorSystem, d.gama,d.pValue ,wprc.`level`,wprc.event_probability eventProbability\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT b.*,c.id evaluationTunnelWsbzPossibilityId,c.expert_id expertId,c.wsbz_possibility_rank_criteria_id wsbzPossibilityRankCriteriaId ,\n" +
			"\t\t\tc.gas_content gasContent, c.cave_ventilation caveVentilation, c.p_value pValue,\n" +
			"\t\t\tc.machine_explosion_proof machineExplosionProof, c.gas_monitor_system gasMonitorSystem\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT a.*,e.gama gama\n" +
			"\t\t\tFROM\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT n.tunnel_id tunnelId,segment_id segmentId,n.id sectionId,\n" +
			"\t\t\tname ,tunnel_line_name tunnelLineName,\n" +
			"\t\t\tCONCAT(start_station,'~',end_station) staction,n.rock_grade rockGrade\n" +
			"\t\t\tFROM `segment` t, section n\n" +
			"\t\t\tWHERE t.id = n.segment_id and n.tunnel_id=#{tunnelId} AND t.is_deleted = 0 AND n.is_deleted =0\n" +
			"\t\t\t)a, evaluation_tunnel_safe_management e\n" +
			"\t\t\tWHERE a.segmentId = e.segment_id AND e.is_deleted =0\n" +
			"\t\t\t)b\n" +
			"\t\t\tLEFT JOIN\n" +
			"\t\t\t(\n" +
			"\t\t\tSELECT *\n" +
			"\t\t\tFROM evaluation_tunnel_wsbz_possibility et \n" +
			"\t\t\tWHERE expert_id = #{expertId}\n" +
			"\t\t\t)c\n" +
			"\t\t\tON b.sectionId = c.section_id AND c.is_deleted =0\n" +
			"\t\t\t)d\n" +
			"\t\t\tLEFT JOIN wsbz_possibility_rank_criteria wprc\n" +
			"\t\t\tON d.wsbzPossibilityRankCriteriaId = wprc.id\n" +
			"\t\t\tORDER BY tunnelLineName,staction")
	List<ExportEvaluationTunnelWsbzPossibilityVo> exportList(String tunnelId, String expertId);

}
