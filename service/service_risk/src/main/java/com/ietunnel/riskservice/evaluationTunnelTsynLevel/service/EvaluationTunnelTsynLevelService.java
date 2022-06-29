package com.ietunnel.riskservice.evaluationTunnelTsynLevel.service;

import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.EvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.ExportEvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.EvaluationTunnelTsynLevel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface EvaluationTunnelTsynLevelService extends IService<EvaluationTunnelTsynLevel> {

    List<EvaluationTunnelTsynLevelVo> getList(String tunnelId);
    List<ExportEvaluationTunnelTsynLevelVo> exportList(String tunnelId);

}