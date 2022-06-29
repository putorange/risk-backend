package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.service;

import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.EvaluationTunnelCollapseLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.EvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.ExportEvaluationTunnelCollapseLevelVo;

import java.util.List;

public interface EvaluationTunnelCollapseLevelService extends IService<EvaluationTunnelCollapseLevel> {

    List<EvaluationTunnelCollapseLevelVo> getList(String tunnelId);

    List<ExportEvaluationTunnelCollapseLevelVo> exportList(String tunnelId);

}