package com.ietunnel.riskservice.specialRisk.mapper;

import com.ietunnel.riskservice.specialRisk.entity.TunnelDivisionWork;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 隧道分部工程 Mapper 接口
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@Mapper
public interface TunnelDivisionWorkMapper extends BaseMapper<TunnelDivisionWork> {
    // 返回所有分部工程（属性包含children{分项工程}）
    @Select("select * from tunnel_division_work where is_deleted =0")
    List<TunnelDivisionWork> selectAllDivisionWork();
}
