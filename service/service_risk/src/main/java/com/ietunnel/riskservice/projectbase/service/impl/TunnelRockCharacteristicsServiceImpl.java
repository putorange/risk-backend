package com.ietunnel.riskservice.projectbase.service.impl;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.ietunnel.riskservice.projectbase.entity.vo.ExportTunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.mapper.TunnelRockCharacteristicsMapper;
import com.ietunnel.riskservice.projectbase.mapper.TunnelRockMapper;
import com.ietunnel.riskservice.projectbase.service.TunnelRockCharacteristicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 围岩级别与围岩特征表 服务实现类
 * </p>
 *
 * @author wxm
 * @since 2022-04-11
 */
@Service
public class TunnelRockCharacteristicsServiceImpl extends ServiceImpl<TunnelRockCharacteristicsMapper, TunnelRockCharacteristics> implements TunnelRockCharacteristicsService {

    @Override
    public List<TunnelRockCharacteristicsVo> getTunnelRockInfo() {
        List<TunnelRockCharacteristicsVo> tunnelRockList = baseMapper.getTunnelRockInfo();
        return tunnelRockList;
    }

    @Override
    public List<ExportTunnelRockCharacteristicsVo> exportList(String tunnelId) {
        List<ExportTunnelRockCharacteristicsVo> list = baseMapper.exportList(tunnelId);
        return list;
    }
}
