package com.ietunnel.riskservice.evaluationTunnelRiskSource.service.impl;

import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.EvaluationRiskSourceTunnel;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.Vo.ExportEvaluationTunnelRiskSourceVo;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.mapper.EvaluationRiskSourceTunnelMapper;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.service.EvaluationRiskSourceTunnelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationRiskSourceTunnelServiceImpl extends ServiceImpl<EvaluationRiskSourceTunnelMapper, EvaluationRiskSourceTunnel> implements EvaluationRiskSourceTunnelService {

    @Override
    public List<EvaluationRiskSourceTunnel> selectAll(String tunnelId) {
        List<EvaluationRiskSourceTunnel> list = baseMapper.selectAll(tunnelId);
        return list;
    }

    @Override
    public List<ExportEvaluationTunnelRiskSourceVo> exportList(String tunnelId) {
        List<ExportEvaluationTunnelRiskSourceVo> list = baseMapper.exportList(tunnelId);
        return list;
    }

    @Override
    public List<EvaluationRiskSourceTunnel> remove(String id) {
        List<EvaluationRiskSourceTunnel> evaluationRiskSourceTunnels = baseMapper.remove(id);
        return evaluationRiskSourceTunnels;
    }
}