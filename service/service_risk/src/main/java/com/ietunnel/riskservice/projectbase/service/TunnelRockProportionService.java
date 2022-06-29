package com.ietunnel.riskservice.projectbase.service;

import com.ietunnel.riskservice.projectbase.entity.TunnelRockProportion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockProportionVo;

import java.util.List;

/**
 * <p>
 * 隧道围岩4、5级所占比例 服务类
 * </p>
 *
 * @author wxm
 * @since 2022-04-22
 */
public interface TunnelRockProportionService extends IService<TunnelRockProportion> {

    List<TunnelRockProportionVo> getTunnelRockPrpportion();
    List<TunnelRockProportionVo> exportList(String id);
}
