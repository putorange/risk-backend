package com.ietunnel.riskservice.specialRisk.service;

import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 隧道施工作业活动与典型事故类型 服务类
 * </p>
 *
 * @author zxg
 * @since 2022-04-29
 */
public interface EvaluationWorkEventService extends IService<EvaluationWorkEvent> {
    EvaluationWorkEvent getByName(String name);

}
