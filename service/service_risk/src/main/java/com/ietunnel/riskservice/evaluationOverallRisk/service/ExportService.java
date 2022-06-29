package com.ietunnel.riskservice.evaluationOverallRisk.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;


import java.util.List;


public interface ExportService extends IService<EportEvalutionOverallRisk> {
    List<EportEvalutionOverallRisk> selectALLList(String tunnelID,String expertId);
}
