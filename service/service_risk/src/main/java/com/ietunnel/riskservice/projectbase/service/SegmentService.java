package com.ietunnel.riskservice.projectbase.service;

import com.ietunnel.riskservice.projectbase.entity.OfficeSegmentClass.OneSegment;
import com.ietunnel.riskservice.projectbase.entity.Segment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 标段 服务类
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
public interface SegmentService extends IService<Segment> {

    List<OneSegment> getAllOneTowSubject();
}
