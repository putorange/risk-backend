package com.ietunnel.riskservice.evaluationTunnelWsbzLevel.service;

import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.EvaluationTunnelWsbzLevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.EvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.ExportEvaluationTunnelWsbzLevelVo;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸风险等级打分表 服务类
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
public interface EvaluationTunnelWsbzLevelService extends IService<EvaluationTunnelWsbzLevel> {

	List<EvaluationTunnelWsbzLevelVo> getList(String tunnelId, String expertId);

	List<ExportEvaluationTunnelWsbzLevelVo> exportList(String tunnelId,String expertId);

}
