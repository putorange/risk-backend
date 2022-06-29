package com.ietunnel.riskservice.significantRisk.mapper;

import com.ietunnel.riskservice.significantRisk.entity.SignificantRisk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 重大风险事故表 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@Mapper
public interface SignificantRiskMapper extends BaseMapper<SignificantRisk> {
    @Select("select * from significant_risk where is_deleted =0")
    List<SignificantRisk> selectAllSignificantRisk();

}
