package com.ietunnel.riskservice.evaluationRiskEstimate.service.impl;

import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.evaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.exportEvaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.entity.EvaluationRiskEstimate;
import com.ietunnel.riskservice.evaluationRiskEstimate.mapper.EvaluationRiskEstimateMapper;
import com.ietunnel.riskservice.evaluationRiskEstimate.service.EvaluationRiskEstimateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 风险估测汇总表 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2022-06-13
 */
@Service
public class EvaluationRiskEstimateServiceImpl extends ServiceImpl<EvaluationRiskEstimateMapper, EvaluationRiskEstimate> implements EvaluationRiskEstimateService {

    @Override
    public List<evaluationRiskEstimateVo> getevaluationRiskEstimateVo(String tunnelId ,String expertId) {
        List<evaluationRiskEstimateVo> evaluationRiskEstimateVos = baseMapper.getevaluationRiskEstimateVo(tunnelId, expertId);
        for (int i = 0; i < evaluationRiskEstimateVos.size(); i++) {
            if (evaluationRiskEstimateVos.get(i).getOldhurtLevel() != null && evaluationRiskEstimateVos.get(i).getOldhurtLevel().length()>0){
                evaluationRiskEstimateVos.get(i).setHurtLevel(evaluationRiskEstimateVos.get(i).getOldhurtLevel().split(","));
            }
        }
        return evaluationRiskEstimateVos;
    }

    @Override
    public List<exportEvaluationRiskEstimateVo> getExportList(String tunnelId, String expertId) {
        return baseMapper.getExportList(tunnelId,expertId);
    }
}
