package com.ietunnel.riskservice.evaluationTunnelTsynLevel.service.impl;

import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.EvaluationTunnelTsynLevel;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.EvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.ExportEvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.mapper.EvaluationTunnelTsynLevelMapper;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.service.EvaluationTunnelTsynLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationTunnelTsynLevelServiceImpl extends ServiceImpl<EvaluationTunnelTsynLevelMapper, EvaluationTunnelTsynLevel> implements EvaluationTunnelTsynLevelService {

    @Override
    public List<EvaluationTunnelTsynLevelVo> getList(String tunnelId) {
        List<EvaluationTunnelTsynLevelVo> list = baseMapper.getList(tunnelId);
        return list;
    }

    @Override
    public List<ExportEvaluationTunnelTsynLevelVo> exportList(String tunnelId) {
        List<ExportEvaluationTunnelTsynLevelVo> list = baseMapper.exportList(tunnelId);
        return list;
    }
}