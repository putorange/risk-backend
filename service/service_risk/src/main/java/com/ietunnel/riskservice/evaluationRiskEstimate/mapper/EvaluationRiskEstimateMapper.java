package com.ietunnel.riskservice.evaluationRiskEstimate.mapper;

import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.evaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.exportEvaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.entity.EvaluationRiskEstimate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 风险估测汇总表 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-06-13
 */
public interface EvaluationRiskEstimateMapper extends BaseMapper<EvaluationRiskEstimate> {

    @Select("SELECT a.*, e.id ,e.hurt_level oldhurtLevel,e.L_score lscore ,e.E_score escore, e.C_score cscore,e.D_score dscore,e.level,e.danger_degree dangerDegree\n" +
            "FROM\n" +
            "(\n" +
            "\tSELECT id evaluationRiskSourceTunnelId,tunnel_id tunnelId,expert_id expertId,tunnel_division_work tunnelDivisionWork, tunnel_unit_work_content tunnelUnitWorkContent,risk_event riskEvent\n" +
            "\tfrom evaluation_risk_source_tunnel\n" +
            "\tWHERE tunnel_id = #{tunnelId} and expert_id = #{expertId} AND is_deleted = 0\n" +
            ")a\n" +
            "LEFT JOIN evaluation_risk_estimate e\n" +
            "On a.tunnelId = e.tunnel_id and a.expertId = e.expert_id and a.evaluationRiskSourceTunnelId = e.evaluation_risk_source_tunnel_id and e.is_deleted = 0\n" +
            "ORDER BY a.tunnelDivisionWork")
    List<evaluationRiskEstimateVo> getevaluationRiskEstimateVo (String tunnelId,String expertId);



    @Select("SELECT a.tunnelDivisionWork,a.tunnelUnitWorkContent,a.riskEvent , e.hurt_level oldhurtLevel,e.L_score lscore ,e.E_score escore, e.C_score cscore,e.D_score dscore,e.level,e.danger_degree dangerDegree\n" +
            "FROM\n" +
            "(\n" +
            "\tSELECT id evaluationRiskSourceTunnelId,tunnel_id tunnelId,expert_id expertId,tunnel_division_work tunnelDivisionWork, tunnel_unit_work_content tunnelUnitWorkContent,risk_event riskEvent\n" +
            "\tfrom evaluation_risk_source_tunnel\n" +
            "\tWHERE tunnel_id = #{tunnelId} and expert_id = #{expertId} AND is_deleted = 0\n" +
            ")a\n" +
            "LEFT JOIN evaluation_risk_estimate e\n" +
            "On a.tunnelId = e.tunnel_id and a.expertId = e.expert_id and a.evaluationRiskSourceTunnelId = e.evaluation_risk_source_tunnel_id and e.is_deleted = 0\n" +
            "ORDER BY a.tunnelDivisionWork")
    List<exportEvaluationRiskEstimateVo> getExportList (String tunnelId,String expertId);

}
