package com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: OneProject
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2022/4/10
 **/
@Data
public class OneProject {
    private String id;
    private String name;
    // 一个一级分类有多个二级分类
    private List<TwoProject> children = new ArrayList<>();
}
