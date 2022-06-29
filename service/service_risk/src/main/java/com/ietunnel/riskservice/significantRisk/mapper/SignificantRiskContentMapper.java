package com.ietunnel.riskservice.significantRisk.mapper;

import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 重大风险源内容 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@Mapper
public interface SignificantRiskContentMapper extends BaseMapper<SignificantRiskContent> {
    @Select("select * from significant_risk_content where is_deleted =0")
    List<SignificantRiskContent> selectAllSignificantRiskContent();
}
