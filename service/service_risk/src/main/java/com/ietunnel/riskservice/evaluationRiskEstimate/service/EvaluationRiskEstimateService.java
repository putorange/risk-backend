package com.ietunnel.riskservice.evaluationRiskEstimate.service;

import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.evaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.exportEvaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.entity.EvaluationRiskEstimate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 风险估测汇总表 服务类
 * </p>
 *
 * @author wxb
 * @since 2022-06-13
 */
public interface EvaluationRiskEstimateService extends IService<EvaluationRiskEstimate> {

    List<evaluationRiskEstimateVo> getevaluationRiskEstimateVo (String tunnelId,String expertId);

    List<exportEvaluationRiskEstimateVo> getExportList (String tunnelId, String expertId);

}
