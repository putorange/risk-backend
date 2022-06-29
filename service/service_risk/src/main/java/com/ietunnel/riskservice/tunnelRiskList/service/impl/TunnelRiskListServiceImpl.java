package com.ietunnel.riskservice.tunnelRiskList.service.impl;

import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.tunnelRiskList.entity.TunnelRiskList;
import com.ietunnel.riskservice.tunnelRiskList.entity.vo.TunnelRiskListVo;
import com.ietunnel.riskservice.tunnelRiskList.mapper.TunnelRiskListMapper;
import com.ietunnel.riskservice.tunnelRiskList.service.TunnelRiskListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道危险源普查清单表 服务实现类
 * </p>
 *
 * @author cmy
 * @since 2022-05-25
 */
@Service
public class TunnelRiskListServiceImpl extends ServiceImpl<TunnelRiskListMapper, TunnelRiskList> implements TunnelRiskListService {

    public List<TunnelRiskListVo> getTunnelRiskInfo() {
        List<TunnelRiskListVo> tunnelRiskList = baseMapper.getTunnelRiskInfo();
        return tunnelRiskList;
    }

}


