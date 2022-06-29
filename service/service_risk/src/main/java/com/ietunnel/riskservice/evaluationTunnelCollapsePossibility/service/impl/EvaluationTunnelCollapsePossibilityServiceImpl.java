package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.service.impl;

import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.EvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity.EvaluationTunnelCollapsePossibility;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.mapper.EvaluationTunnelCollapsePossibilityMapper;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.service.EvaluationTunnelCollapsePossibilityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道各区段坍塌事故可能性评估打分表 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2022-06-09
 */
@Service
public class EvaluationTunnelCollapsePossibilityServiceImpl extends ServiceImpl<EvaluationTunnelCollapsePossibilityMapper, EvaluationTunnelCollapsePossibility> implements EvaluationTunnelCollapsePossibilityService {


    @Override
    public List<EvaluationTunnelCollapsePossibilityVo> getList(String tunelId, String expertId) {
        List<EvaluationTunnelCollapsePossibilityVo> list = baseMapper.getList(tunelId,expertId);
        return list;
    }

    @Override
    public List<ExportEvaluationTunnelCollapsePossibilityVo> exportList(String tunelId,String expertId) {
        List<ExportEvaluationTunnelCollapsePossibilityVo> exportEvaluationTunnelCollapsePossibilityVos = baseMapper.exportList(tunelId,expertId);
        return exportEvaluationTunnelCollapsePossibilityVos;
    }
}
