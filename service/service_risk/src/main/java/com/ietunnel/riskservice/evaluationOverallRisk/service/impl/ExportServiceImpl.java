package com.ietunnel.riskservice.evaluationOverallRisk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ietunnel.riskservice.evaluationOverallRisk.mapper.ExportMapper;
import com.ietunnel.riskservice.evaluationOverallRisk.service.ExportService;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExportServiceImpl extends ServiceImpl<ExportMapper, EportEvalutionOverallRisk> implements ExportService {



    @Override
    public List<EportEvalutionOverallRisk> selectALLList(String tunnelID,String expertId) {
        return baseMapper.selectALLList(tunnelID,expertId);
    }
}
