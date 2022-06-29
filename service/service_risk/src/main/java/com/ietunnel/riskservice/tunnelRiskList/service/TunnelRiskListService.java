package com.ietunnel.riskservice.tunnelRiskList.service;

import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.tunnelRiskList.entity.TunnelRiskList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.tunnelRiskList.entity.vo.TunnelRiskListVo;

import java.util.List;

/**
 * <p>
 * 隧道危险源普查清单表 服务类
 * </p>
 *
 * @author cmy
 * @since 2022-05-25
 */
public interface TunnelRiskListService extends IService<TunnelRiskList> {
    List<TunnelRiskListVo> getTunnelRiskInfo();
}

