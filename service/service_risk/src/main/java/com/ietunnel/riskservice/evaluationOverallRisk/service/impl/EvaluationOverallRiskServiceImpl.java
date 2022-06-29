package com.ietunnel.riskservice.evaluationOverallRisk.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationOverallRisk.entity.EvaluationOverallRisk;
import com.ietunnel.riskservice.evaluationOverallRisk.mapper.EvaluationOverallRiskMapper;
import com.ietunnel.riskservice.evaluationOverallRisk.service.EvaluationOverallRiskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 总体风险评估指标体系打分表 服务实现类
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
@Service
public class EvaluationOverallRiskServiceImpl extends ServiceImpl<EvaluationOverallRiskMapper, EvaluationOverallRisk> implements EvaluationOverallRiskService {

    @Override
    public void query(Page pageParam) {
        List<EvaluationOverallRiskVo> records = baseMapper.selectMajorList(pageParam);
        pageParam.setRecords(records);
        pageParam.setTotal(records.size());
    }

    @Override
    public List<EvaluationOverallRiskVo> queryall() {
        List<EvaluationOverallRiskVo> voList = baseMapper.selectALLList();
        return voList;
    }

    @Override
    public List<EvaluationOverallRiskVo> selectALLListBytunnelid(String tunnelid, String expertId) {
        return baseMapper.selectALLListBytunnelid(tunnelid,expertId);
    }

    @Override
    public int updateByeId(EvaluationOverallRisk evaluationOverallRisk) {
        int i = baseMapper.updateByeId(evaluationOverallRisk);
        return i;
    }

    @Override
    public int delId(String evaluationId) {
        return baseMapper.delId(evaluationId);
    }


}
