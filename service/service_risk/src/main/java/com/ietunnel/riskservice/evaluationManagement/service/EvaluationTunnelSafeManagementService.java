package com.ietunnel.riskservice.evaluationManagement.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationManagement.entity.EvaluationTunnelSafeManagement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationManagement.entity.vo.EvaluationTunnelSafeManagementVo;
import com.ietunnel.riskservice.evaluationOverallRisk.entity.EvaluationOverallRisk;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;

import java.util.List;

/**
 * <p>
 * 隧道安全管理评估指标打分表 服务类
 * </p>
 *
 * @author cmy
 * @since 2022-05-22
 */
public interface EvaluationTunnelSafeManagementService extends IService<EvaluationTunnelSafeManagement> {
    void query(Page pageParam);

    List<EvaluationTunnelSafeManagementVo> queryall();

}

