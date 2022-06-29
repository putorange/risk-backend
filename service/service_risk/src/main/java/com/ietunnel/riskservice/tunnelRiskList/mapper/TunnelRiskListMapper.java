package com.ietunnel.riskservice.tunnelRiskList.mapper;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.tunnelRiskList.entity.TunnelRiskList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.tunnelRiskList.entity.vo.TunnelRiskListVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 隧道危险源普查清单表 Mapper 接口
 * </p>
 *
 * @author cmy
 * @since 2022-05-25
 */
@Repository
public interface TunnelRiskListMapper extends BaseMapper<TunnelRiskList> {
    @Select("SELECT DISTINCT l.division_id id, tdw.name name,l.potential_event potentialEvent \n" +
            "FROM tunnel_division_work tdw,tunnel_risk_list l\n" +
            "WHERE tdw.id = l.division_id and l.is_deleted = 0")
    List<TunnelRiskListVo> getTunnelRiskInfo();
}

