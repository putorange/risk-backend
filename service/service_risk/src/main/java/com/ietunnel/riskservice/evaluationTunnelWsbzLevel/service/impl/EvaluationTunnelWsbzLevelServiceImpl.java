package com.ietunnel.riskservice.evaluationTunnelWsbzLevel.service.impl;

import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.EvaluationTunnelWsbzLevel;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.mapper.EvaluationTunnelWsbzLevelMapper;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.service.EvaluationTunnelWsbzLevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.EvaluationTunnelWsbzLevelVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzLevel.entity.Vo.ExportEvaluationTunnelWsbzLevelVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸风险等级打分表 服务实现类
 * </p>
 *
 * @author wxm
 * @since 2022-06-25
 */
@Service
public class EvaluationTunnelWsbzLevelServiceImpl extends ServiceImpl<EvaluationTunnelWsbzLevelMapper, EvaluationTunnelWsbzLevel> implements EvaluationTunnelWsbzLevelService {

	@Override
	public List<EvaluationTunnelWsbzLevelVo> getList(String tunnelId, String expertId) {
		List<EvaluationTunnelWsbzLevelVo> list = baseMapper.getList(tunnelId, expertId);
		return list;
	}

	@Override
	public List<ExportEvaluationTunnelWsbzLevelVo> exportList(String tunnelId, String expertId) {
		List<ExportEvaluationTunnelWsbzLevelVo> list = baseMapper.exportList(tunnelId, expertId);
		return list;
	}

}
