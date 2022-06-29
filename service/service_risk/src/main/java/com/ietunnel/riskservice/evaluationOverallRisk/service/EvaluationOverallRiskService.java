package com.ietunnel.riskservice.evaluationOverallRisk.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationOverallRisk.entity.EvaluationOverallRisk;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;


import java.util.List;

/**
 * <p>
 * 总体风险评估指标体系打分表 服务类
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
public interface EvaluationOverallRiskService extends IService<EvaluationOverallRisk> {
    void query(Page pageParam);

    List<EvaluationOverallRiskVo> queryall();


    List<EvaluationOverallRiskVo> selectALLListBytunnelid(String tunnelid, String expertId);

    int updateByeId(EvaluationOverallRisk evaluationOverallRisk);

    int delId(String evaluationId);
}
