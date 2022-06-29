package com.ietunnel.riskservice.evaluationTunnelRiskSource.service;

import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.EvaluationRiskSourceTunnel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.Vo.ExportEvaluationTunnelRiskSourceVo;

import java.util.List;

public interface EvaluationRiskSourceTunnelService extends IService<EvaluationRiskSourceTunnel> {

    List<EvaluationRiskSourceTunnel> selectAll(String tunnelId);
    List<ExportEvaluationTunnelRiskSourceVo> exportList(String tunnelId);
    List<EvaluationRiskSourceTunnel> remove(String id);

}