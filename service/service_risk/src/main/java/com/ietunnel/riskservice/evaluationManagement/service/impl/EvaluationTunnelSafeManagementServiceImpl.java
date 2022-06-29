package com.ietunnel.riskservice.evaluationManagement.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.evaluationManagement.entity.EvaluationTunnelSafeManagement;
import com.ietunnel.riskservice.evaluationManagement.mapper.EvaluationTunnelSafeManagementMapper;
import com.ietunnel.riskservice.evaluationManagement.service.EvaluationTunnelSafeManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ietunnel.riskservice.evaluationManagement.entity.vo.EvaluationTunnelSafeManagementVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道安全管理评估指标打分表 服务实现类
 * </p>
 *
 * @author cmy
 * @since 2022-05-22
 */
@Service
public class EvaluationTunnelSafeManagementServiceImpl extends ServiceImpl<EvaluationTunnelSafeManagementMapper, EvaluationTunnelSafeManagement> implements EvaluationTunnelSafeManagementService {
    @Override
    public void query(Page pageParam) {
        List<EvaluationTunnelSafeManagementVo> records = baseMapper.selectMajorList(pageParam);
        pageParam.setRecords(records);
        pageParam.setTotal(records.size());
    }

    @Override
    public List<EvaluationTunnelSafeManagementVo> queryall() {
        List<EvaluationTunnelSafeManagementVo> voList = baseMapper.selectALLList();
        return voList;
    }
}

