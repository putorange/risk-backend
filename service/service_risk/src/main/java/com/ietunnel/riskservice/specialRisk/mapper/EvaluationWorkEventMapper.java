package com.ietunnel.riskservice.specialRisk.mapper;

import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.jmx.export.annotation.ManagedNotifications;

import java.util.List;

/**
 * <p>
 * 隧道施工作业活动与典型事故类型 Mapper 接口
 * </p>
 *
 * @author zxg
 * @since 2022-04-29
 */
@Mapper
public interface EvaluationWorkEventMapper extends BaseMapper<EvaluationWorkEvent> {
    @Select("select * from evaluation_work_event where is_deleted =0")
    List<EvaluationWorkEvent> selectAllEvaluationWorkEvent();
}
