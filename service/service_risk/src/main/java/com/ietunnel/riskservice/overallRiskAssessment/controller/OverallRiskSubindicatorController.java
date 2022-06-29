package com.ietunnel.riskservice.overallRiskAssessment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskClassification;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskSubindicator;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskClassificationService;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskSubindicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 总体风险子评估指标体系子评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/overallRiskAssessment/overall-risk-subindicator")
@CrossOrigin
public class OverallRiskSubindicatorController {
    @Autowired
    private OverallRiskSubindicatorService overallRiskSubindicatorService;

    @Autowired
    private OverallRiskClassificationService overallRiskClassificationService;

    @PostMapping("/addOverallRiskSubindicator")
    public R addlevel(@RequestBody OverallRiskSubindicator overallRiskSubindicator){
        boolean save = overallRiskSubindicatorService.save(overallRiskSubindicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllOverallRiskSubindicator/{indicatorId}")
    public R findAllOverallRiskSubindicator(@PathVariable String indicatorId){
        QueryWrapper<OverallRiskSubindicator> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id", indicatorId);
        List<OverallRiskSubindicator> list = overallRiskSubindicatorService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findOverallRiskSubindicatorByid/{id}")
    public R findOverallRiskSubindicatorByid(@PathVariable String id){
        OverallRiskSubindicator overallRiskSubindicator = overallRiskSubindicatorService.getById(id);
        return R.ok().data("OverallRiskSubindicator",overallRiskSubindicator);
    }

    @PostMapping("/updateOverallRiskSubindicator")
    public R updateOverallRiskSubindicator(@RequestBody OverallRiskSubindicator overallRiskSubindicator){
        boolean update = overallRiskSubindicatorService.updateById(overallRiskSubindicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeOverallRiskSubindicator/{id}")
    public R removeOverallRiskSubindicator(@PathVariable String id){
        boolean remove = overallRiskSubindicatorService.removeById(id);
        QueryWrapper<OverallRiskClassification> wrapper1 = new QueryWrapper<>();
        QueryWrapper<OverallRiskClassification> queryWrapper1 = wrapper1.select("id").eq("subindicator_id", id);
        List<OverallRiskClassification> list1 = overallRiskClassificationService.list(queryWrapper1);
        List<String> collect1 = list1.stream().map(OverallRiskClassification::getId).collect(Collectors.toList());
        for (String s1 : collect1) {
            overallRiskClassificationService.removeById(s1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

