package com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.service.impl;

import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.EvaluationTunnelWsbzPossibility;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.EvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.entity.Vo.ExportEvaluationTunnelWsbzPossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.mapper.EvaluationTunnelWsbzPossibilityMapper;
import com.ietunnel.riskservice.evaluationTunnelWsbzPossibility.service.EvaluationTunnelWsbzPossibilityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道各区段瓦斯爆炸可能性评估打分表 服务实现类
 * </p>
 *
 * @author wxm
 * @since 2022-06-24
 */
@Service
public class EvaluationTunnelWsbzPossibilityServiceImpl extends ServiceImpl<EvaluationTunnelWsbzPossibilityMapper, EvaluationTunnelWsbzPossibility> implements EvaluationTunnelWsbzPossibilityService {

	@Override
	public List<EvaluationTunnelWsbzPossibilityVo> getList(String tunnelId, String expertId) {
		List<EvaluationTunnelWsbzPossibilityVo> list = baseMapper.getList(tunnelId, expertId);
		return list;
	}

	@Override
	public List<ExportEvaluationTunnelWsbzPossibilityVo> exportList(String tunnelId, String expertId) {
		List<ExportEvaluationTunnelWsbzPossibilityVo> list = baseMapper.exportList(tunnelId, expertId);
		return list;
	}

}
