package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.service.impl;

import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.EvaluationTunnelCollapseLevel;

import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.EvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.ExportEvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.mapper.EvaluationTunnelCollapseLevelMapper;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.service.EvaluationTunnelCollapseLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EvaluationTunnelCollapseLevelServiceImpl extends ServiceImpl<EvaluationTunnelCollapseLevelMapper, EvaluationTunnelCollapseLevel> implements EvaluationTunnelCollapseLevelService {

    @Override
    public List<EvaluationTunnelCollapseLevelVo> getList(String tunnelId) {
        List<EvaluationTunnelCollapseLevelVo> list = baseMapper.getList(tunnelId);
        return list;
    }

    @Override
    public List<ExportEvaluationTunnelCollapseLevelVo> exportList(String tunnelId) {
        List<ExportEvaluationTunnelCollapseLevelVo> list = baseMapper.exportList(tunnelId);
        return list;
    }
}

