package com.ietunnel.riskservice.projectbase.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ietunnel.riskservice.projectbase.entity.vo.ExportTunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TunnelRockCharacteristicsMapper extends BaseMapper<TunnelRockCharacteristics> {

    @Select("SELECT DISTINCT s.tunnel_line_name tunnelLineName,s.rock_grade rockGrade,s.basic_quality basicQuality,s.qualitative_characteristics qualitativeCharacteristics,s.start_station startStation,s.end_station endStation\n" +
            "                      FROM tunnel_rock_characteristics trc,section s \n" +
            "                      WHERE trc.tunnel_id = s.tunnel_id AND  s.is_deleted = 0 \n" +
            "\t\t\t\t\t\t\t\t\t\t\tORDER BY s.tunnel_line_name ASC, s.basic_quality DESC")
    List<TunnelRockCharacteristicsVo> getTunnelRockInfo();

    @Select("SELECT DISTINCT s.tunnel_line_name tunnelLineName,s.rock_grade rockGrade,s.basic_quality basicQuality,s.qualitative_characteristics qualitativeCharacteristics,CONCAT(s.start_station,'~',s.end_station) staction\n" +
            "                      FROM tunnel_rock_characteristics trc,section s \n" +
            "                      WHERE trc.tunnel_id = s.tunnel_id AND trc.tunnel_id = #{tunnelId} AND  s.is_deleted = 0 \n" +
            "\t\t\t\t\t\t\t\t\t\t\tORDER BY s.tunnel_line_name ASC, s.basic_quality DESC")
    List<ExportTunnelRockCharacteristicsVo> exportList(String tunnelId);

}