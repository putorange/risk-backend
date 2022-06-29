package com.ietunnel.riskservice.specialRisk.service;

import com.ietunnel.riskservice.specialRisk.entity.TunnelDivisionWork;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.specialRisk.entity.vo.DivisionWork;

import java.util.List;

/**
 * <p>
 * 隧道分部工程 服务类
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
public interface TunnelDivisionWorkService extends IService<TunnelDivisionWork> {

    List<DivisionWork> getAllDivisionWork();

    boolean updateByList(DivisionWork divisionWork);
}
