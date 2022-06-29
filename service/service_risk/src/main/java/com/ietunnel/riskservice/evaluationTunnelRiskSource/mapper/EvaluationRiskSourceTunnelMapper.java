package com.ietunnel.riskservice.evaluationTunnelRiskSource.mapper;

import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.EvaluationRiskSourceTunnel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.Vo.ExportEvaluationTunnelRiskSourceVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EvaluationRiskSourceTunnelMapper extends BaseMapper<EvaluationRiskSourceTunnel> {

    @Select("SELECT * FROM `evaluation_risk_source_tunnel` WHERE tunnel_id = #{tunnelId} AND is_deleted = 0 ORDER BY tunnel_division_work")
    List<EvaluationRiskSourceTunnel> selectAll(String tunnelId);

    @Select("SELECT tunnel_division_work tunnelDivisionWork,tunnel_unit_work_content tunnelUnitWorkContent,risk_event riskEvent,hurt_type hurtType,hurt_level hurtLevel,unsafe_state unsafeState,unsafe_behavior unsafeBehavior\n" +
            "FROM `evaluation_risk_source_tunnel` WHERE tunnel_id = #{tunnelId} AND is_deleted = 0 ORDER BY tunnel_division_work")
    List<ExportEvaluationTunnelRiskSourceVo> exportList(String tunnelId);

    @Select("update evaluation_risk_source_tunnel set is_deleted = 1 where id=#{id}")
    List<EvaluationRiskSourceTunnel> remove(String id);
}


