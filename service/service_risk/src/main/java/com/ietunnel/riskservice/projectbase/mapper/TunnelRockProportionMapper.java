package com.ietunnel.riskservice.projectbase.mapper;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockProportion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockProportionVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TunnelRockProportionMapper extends BaseMapper<TunnelRockProportion> {

    @Select("SELECT DISTINCT tr.rock_grade as rockGrade,CONCAT(SUM(tr.proportion))\n" +
            "as proportion FROM  tunnel_rock_proportion trp,tunnel_rock tr \n" +
            "            WHERE trp.tunnel_id = tr.tunnel_id AND tr.is_deleted = 0 GROUP BY tr.rock_grade ORDER BY tr.rock_grade DESC")
    List<TunnelRockProportionVo> getTunnelRockPrpportion();

    @Select("SELECT DISTINCT tr.rock_grade as rockGrade,CONCAT(SUM(tr.proportion),'%')\n" +
            "as proportion FROM  tunnel_rock_proportion trp,tunnel_rock tr \n" +
            "            WHERE trp.tunnel_id = tr.tunnel_id AND trp.tunnel_id = #{tunnelId} AND tr.is_deleted = 0 GROUP BY tr.rock_grade ORDER BY tr.rock_grade DESC")
    List<TunnelRockProportionVo> exportList(String id);
}