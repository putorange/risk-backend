package com.ietunnel.riskservice.significantRisk.mapper;

import com.ietunnel.riskservice.significantRisk.entity.RiskResourceCheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道风险源检查表 Mapper 接口
 * </p>
 *
 * @author zxg
 * @since 2022-06-07
 */
public interface RiskResourceCheckMapper extends BaseMapper<RiskResourceCheck> {
    @Select("select * from risk_resource_check where is_deleted =0")
    List<RiskResourceCheck> selectAllRiskResourceCheck();

    @Select("select * from risk_resource_check where is_deleted =0 \n" +
            "order by significant_risk_source_id ")
    List<RiskResourceCheck> selectAllRiskResourceCheckByResourceId();

    @Delete("delete  from risk_resource_check where significant_risk_content_id = #{id} ")
    void removeByRiskContentId(String id);

}
