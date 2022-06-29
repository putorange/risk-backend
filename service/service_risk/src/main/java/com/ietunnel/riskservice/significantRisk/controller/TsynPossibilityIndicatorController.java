package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityClassification;
import com.ietunnel.riskservice.significantRisk.entity.TsynPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.entity.TsynPossibilityIndicator;
import com.ietunnel.riskservice.significantRisk.service.TsynPossibilityClassificationService;
import com.ietunnel.riskservice.significantRisk.service.TsynPossibilityIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道突水涌泥可能性评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/tsyn-possibility-indicator")
@CrossOrigin
public class TsynPossibilityIndicatorController {

    @Autowired
    private TsynPossibilityIndicatorService tsynPossibilityIndicatorService;

    @Autowired
    private TsynPossibilityClassificationService tsynPossibilityClassificationService;


    @PostMapping("/addTsynPossibilityIndicator")
    public R addTsynPossibilityIndicator(@RequestBody TsynPossibilityIndicator tsynPossibilityIndicator){
        boolean save = tsynPossibilityIndicatorService.save(tsynPossibilityIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllTsynPossibilityIndicator")
    public R findAllcasualtLevel(){
        List<TsynPossibilityIndicator> list = tsynPossibilityIndicatorService.list(null);
        return R.ok().data("info",list);
    }


    @GetMapping("/findTsynPossibilityIndicatorByid/{id}")
    public R findTsynPossibilityIndicatorByid(@PathVariable String id){
        TsynPossibilityIndicator tsynPossibilityIndicator = tsynPossibilityIndicatorService.getById(id);
        return R.ok().data("TsynPossibilityIndicator",tsynPossibilityIndicator);
    }

    @PostMapping("/updateTsynPossibilityIndicator")
    public R updateTsynPossibilityIndicator(@RequestBody TsynPossibilityIndicator tsynPossibilityIndicator){
        boolean update = tsynPossibilityIndicatorService.updateById(tsynPossibilityIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTsynPossibilityIndicator/{id}")
    public R removeTsynPossibilityIndicator(@PathVariable String id){
        boolean remove = tsynPossibilityIndicatorService.removeById(id);
        QueryWrapper<TsynPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",id);
        List<TsynPossibilityClassification> list = tsynPossibilityClassificationService.list(wrapper);
        for (TsynPossibilityClassification tsynPossibilityClassification : list) {
            String id1 = tsynPossibilityClassification.getId();
            tsynPossibilityClassificationService.removeById(id1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

