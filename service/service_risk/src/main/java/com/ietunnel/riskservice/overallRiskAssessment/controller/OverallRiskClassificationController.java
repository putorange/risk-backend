package com.ietunnel.riskservice.overallRiskAssessment.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskClassification;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallRiskSubindicator;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallRiskClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 总体风险评估指标体系的分类 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/overallRiskAssessment/overall-risk-classification")
@CrossOrigin
public class OverallRiskClassificationController {
    @Autowired
    private OverallRiskClassificationService overallRiskClassificationService;

    @PostMapping("/addOverallRiskClassificationr")
    public R addlevel(@RequestBody  OverallRiskClassification overallRiskClassification){
        boolean save = overallRiskClassificationService.save(overallRiskClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllOverallRiskClassification/{indicatorId}")
    public R findAllOverallRiskClassification(@PathVariable String indicatorId){
        QueryWrapper<OverallRiskClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("subindicator_id", indicatorId);
        List<OverallRiskClassification> list = overallRiskClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findOverallRiskClassificationByid/{id}")
    public R findOverallRiskClassificationByid(@PathVariable String id){
        OverallRiskClassification overallRiskClassification = overallRiskClassificationService.getById(id);
        return R.ok().data("OverallRiskClassification",overallRiskClassification);
    }

    @PostMapping("/updateOverallRiskClassification")
    public R updateOverallRiskClassification(@RequestBody OverallRiskClassification overallRiskClassification){
        boolean update = overallRiskClassificationService.updateById(overallRiskClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeOverallRiskClassification/{id}")
    public R removeOverallRiskClassification(@PathVariable String id){
        boolean remove = overallRiskClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

