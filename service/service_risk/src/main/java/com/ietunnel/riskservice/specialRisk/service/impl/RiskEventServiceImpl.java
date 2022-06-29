package com.ietunnel.riskservice.specialRisk.service.impl;

import com.ietunnel.riskservice.specialRisk.entity.RiskEvent;
import com.ietunnel.riskservice.specialRisk.mapper.RiskEventMapper;
import com.ietunnel.riskservice.specialRisk.service.RiskEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 风险事故表 服务实现类
 * </p>
 *
 * @author wxb
 * @since 2022-01-26
 */
@Service
public class RiskEventServiceImpl extends ServiceImpl<RiskEventMapper, RiskEvent> implements RiskEventService {

}
