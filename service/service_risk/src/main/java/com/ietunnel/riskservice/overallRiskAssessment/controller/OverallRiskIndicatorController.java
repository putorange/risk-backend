package com.ietunnel.riskservice.overallRiskAssessment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskClassification;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskIndicator;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskSubindicator;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskClassificationService;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskIndicatorService;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskSubindicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 总体风险评估指标表体系评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/overallRiskAssessment/overall-risk-indicator")
@CrossOrigin
public class OverallRiskIndicatorController {

    @Autowired
    private OverallRiskIndicatorService overallRiskIndicatorService;

    @Autowired
    private OverallRiskSubindicatorService overallRiskSubindicatorService;

    @Autowired
    private OverallRiskClassificationService overallRiskClassificationService;
    @PostMapping("/addOverallRiskIndicator")
    public R addlevel(@RequestBody OverallRiskIndicator overallRiskIndicator){
        boolean save = overallRiskIndicatorService.save(overallRiskIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllOverallRiskIndicator/{current}/{limit}")
    public R findAllOverallRiskIndicator(@PathVariable Long current, @PathVariable Long limit){
        Page<OverallRiskIndicator> page = new Page<>(current,limit);
        overallRiskIndicatorService.page(page,null);
        long total = page.getTotal();
        List<OverallRiskIndicator> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findOverallRiskIndicatorByid/{id}")
    public R findOverallRiskIndicatorByid(@PathVariable String id){
        OverallRiskIndicator overallRiskIndicator = overallRiskIndicatorService.getById(id);
        return R.ok().data("OverallRiskIndicator",overallRiskIndicator);
    }

    @PostMapping("/updateOverallRiskIndicator")
    public R updateOverallRiskIndicator(@RequestBody OverallRiskIndicator overallRiskIndicator){
        boolean update = overallRiskIndicatorService.updateById(overallRiskIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeOverallRiskIndicator/{id}")
    public R removeOverallRiskIndicator(@PathVariable String id){
        boolean remove = overallRiskIndicatorService.removeById(id);
        QueryWrapper<OverallRiskSubindicator> wrapper = new QueryWrapper<>();
        QueryWrapper<OverallRiskSubindicator> queryWrapper = wrapper.select("id").eq("indicator_id", id);
        List<OverallRiskSubindicator> list = overallRiskSubindicatorService.list(queryWrapper);
        List<String> collect = list.stream().map(OverallRiskSubindicator::getId).collect(Collectors.toList());
        for (String s : collect) {
            overallRiskSubindicatorService.removeById(s);
            QueryWrapper<OverallRiskClassification> wrapper1 = new QueryWrapper<>();
            QueryWrapper<OverallRiskClassification> queryWrapper1 = wrapper1.select("id").eq("subindicator_id", s);
            List<OverallRiskClassification> list1 = overallRiskClassificationService.list(queryWrapper1);
            List<String> collect1 = list1.stream().map(OverallRiskClassification::getId).collect(Collectors.toList());
            for (String s1 : collect1) {
                overallRiskClassificationService.removeById(s1);
            }
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

