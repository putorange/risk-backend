package com.ietunnel.riskservice.projectbase.service.impl;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockProportion;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockProportionVo;
import com.ietunnel.riskservice.projectbase.mapper.TunnelRockProportionMapper;
import com.ietunnel.riskservice.projectbase.service.TunnelRockProportionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 隧道围岩4、5级所占比例 服务实现类
 * </p>
 *
 * @author wxm
 * @since 2022-04-22
 */
@Service
public class TunnelRockProportionServiceImpl extends ServiceImpl<TunnelRockProportionMapper, TunnelRockProportion> implements TunnelRockProportionService {

    @Override
    public List<TunnelRockProportionVo> getTunnelRockPrpportion() {
        List<TunnelRockProportionVo> tunnelRockProportionList = baseMapper.getTunnelRockPrpportion();
        return tunnelRockProportionList;
    }
    @Override
    public List<TunnelRockProportionVo> exportList(String id) {
        List<TunnelRockProportionVo> list = baseMapper.exportList(id);
        return list;
    }

}
