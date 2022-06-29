package com.ietunnel.riskservice.specialRisk.service.impl;

import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.ietunnel.riskservice.specialRisk.mapper.EvaluationWorkEventMapper;
import com.ietunnel.riskservice.specialRisk.service.EvaluationWorkEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 隧道施工作业活动与典型事故类型 服务实现类
 * </p>
 *
 * @author zxg
 * @since 2022-04-29
 */
@Service
public class EvaluationWorkEventServiceImpl extends ServiceImpl<EvaluationWorkEventMapper, EvaluationWorkEvent> implements EvaluationWorkEventService {

    @Override
    public EvaluationWorkEvent getByName(String name) {
        for(EvaluationWorkEvent evaluationWorkEvent: baseMapper.selectAllEvaluationWorkEvent()){
            if(evaluationWorkEvent.getName().equals(name)){
                return evaluationWorkEvent;
            }
        }

        return null;
    }
}
