package com.ietunnel.riskservice.specialRisk.mapper;

import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道单位作业 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@Mapper
public interface TunnelUnitWorkMapper extends BaseMapper<TunnelUnitWork> {

    @Select("select * from tunnel_unit_work where is_deleted =0")
    List<TunnelUnitWork> selectAllTunnelUnitWork();

}
