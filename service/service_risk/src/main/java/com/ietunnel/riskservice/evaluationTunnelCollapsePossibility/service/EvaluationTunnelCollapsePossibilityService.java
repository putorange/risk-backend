package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.service;

import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.EvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity.EvaluationTunnelCollapsePossibility;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 隧道各区段坍塌事故可能性评估打分表 服务类
 * </p>
 *
 * @author wxb
 * @since 2022-06-09
 */
public interface EvaluationTunnelCollapsePossibilityService extends IService<EvaluationTunnelCollapsePossibility> {

    List<EvaluationTunnelCollapsePossibilityVo> getList(String tunelId , String expertId);

    List<ExportEvaluationTunnelCollapsePossibilityVo> exportList(String tunelId,String expertId);

}
