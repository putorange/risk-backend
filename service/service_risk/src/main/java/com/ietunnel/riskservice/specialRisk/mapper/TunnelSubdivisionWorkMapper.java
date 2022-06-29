package com.ietunnel.riskservice.specialRisk.mapper;

import com.ietunnel.riskservice.specialRisk.entity.TunnelSubdivisionWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道分项工程 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@Mapper
public interface TunnelSubdivisionWorkMapper extends BaseMapper<TunnelSubdivisionWork> {
    @Select("select * from tunnel_subdivision_work where is_deleted =0")
    List<TunnelSubdivisionWork> selectAllSubDivisionWork();
}
