package com.ietunnel.riskservice.significantRisk.mapper;

import com.ietunnel.riskservice.significantRisk.entity.SignificantRisk;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskSource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 重大风险源 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@Mapper
public interface SignificantRiskSourceMapper extends BaseMapper<SignificantRiskSource> {
    @Select("select * from significant_risk_source where is_deleted =0")
    List<SignificantRiskSource> selectAllSignificantRiskSource();
}
