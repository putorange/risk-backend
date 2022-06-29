package com.ietunnel.riskservice.significantRisk.service;

import com.ietunnel.riskservice.significantRisk.entity.RiskResourceCheck;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import com.ietunnel.riskservice.significantRisk.entity.vo.RiskResourceCheckTable;

import java.util.List;

/**
 * <p>
 * 隧道风险源检查表 服务类
 * </p>
 *
 * @author zxg
 * @since 2022-06-07
 */
public interface RiskResourceCheckService extends IService<RiskResourceCheck> {

    List<RiskResourceCheckTable> getAllRiskResourceCheck();
    List<RiskResourceCheck> getAllRiskResourceCheckByResourceId();
    void removeByRiskContentId(String id);
    RiskResourceCheck getByRiskContentId(SignificantRiskContent significantRiskContent);

}
