package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityClassification;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityIndicator;
import com.ietunnel.riskservice.significantRisk.service.CollapsePossibilityClassificationService;
import com.ietunnel.riskservice.significantRisk.service.CollapsePossibilityIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道坍塌可能性评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/collapse-possibility-indicator")
@CrossOrigin
public class CollapsePossibilityIndicatorController {

    @Autowired
    private CollapsePossibilityIndicatorService collapsePossibilityIndicatorService;

    @Autowired
    private CollapsePossibilityClassificationService collapsePossibilityClassificationService;


    @PostMapping("/addCollapsePossibilityIndicator")
    public R addCollapsePossibilityIndicator(@RequestBody CollapsePossibilityIndicator CollapsePossibilityIndicator){
        boolean save = collapsePossibilityIndicatorService.save(CollapsePossibilityIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllCollapsePossibilityIndicator")
    public R findAllcasualtLevel(){
        List<CollapsePossibilityIndicator> list = collapsePossibilityIndicatorService.list(null);
        return R.ok().data("info",list);
    }


    @GetMapping("/findCollapsePossibilityIndicatorByid/{id}")
    public R findCollapsePossibilityIndicatorByid(@PathVariable String id){
        CollapsePossibilityIndicator CollapsePossibilityIndicator = collapsePossibilityIndicatorService.getById(id);
        return R.ok().data("CollapsePossibilityIndicator",CollapsePossibilityIndicator);
    }

    @PostMapping("/updateCollapsePossibilityIndicator")
    public R updateCollapsePossibilityIndicator(@RequestBody CollapsePossibilityIndicator CollapsePossibilityIndicator){
        boolean update = collapsePossibilityIndicatorService.updateById(CollapsePossibilityIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeCollapsePossibilityIndicator/{id}")
    public R removeCollapsePossibilityIndicator(@PathVariable String id){
        boolean remove = collapsePossibilityIndicatorService.removeById(id);
        QueryWrapper<CollapsePossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",id);
        List<CollapsePossibilityClassification> list = collapsePossibilityClassificationService.list(wrapper);
        for (CollapsePossibilityClassification riskPossibilityClassification : list) {
            String id1 = riskPossibilityClassification.getId();
            collapsePossibilityClassificationService.removeById(id1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

