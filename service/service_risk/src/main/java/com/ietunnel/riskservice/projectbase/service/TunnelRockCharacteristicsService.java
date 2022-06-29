package com.ietunnel.riskservice.projectbase.service;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.projectbase.entity.vo.ExportTunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;

import java.util.List;

/**
 * <p>
 * 围岩级别与围岩特征表 服务类
 * </p>
 *
 * @author wxm
 * @since 2022-04-11
 */
public interface TunnelRockCharacteristicsService extends IService<TunnelRockCharacteristics> {

    List<TunnelRockCharacteristicsVo> getTunnelRockInfo();
    List<ExportTunnelRockCharacteristicsVo> exportList(String tunnelId);


}

