package com.ietunnel.riskservice.projectbase.entity.OfficeSegmentClass;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: OneSegment
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2022/4/13
 **/
@Data
public class OneSegment {
    private String id;
    private String name;
    // 一个一级分类有多个二级分类
    private List<TwoSegment> children = new ArrayList<>();
}
