package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.service;

import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.EvaluationTunnelWsbzPossibility;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.EvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.ExportEvaluationTunnelWsbzPossibilityVo;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸可能性评估打分表 服务类
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
public interface EvaluationTunnelWsbzPossibilityService extends IService<EvaluationTunnelWsbzPossibility> {

	List<EvaluationTunnelWsbzPossibilityVo> getList(String tunnelId, String expertId);

	List<ExportEvaluationTunnelWsbzPossibilityVo> exportList(String tunnelId, String expertId);

}
