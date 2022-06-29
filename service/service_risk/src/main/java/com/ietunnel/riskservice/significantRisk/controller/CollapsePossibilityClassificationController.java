package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityClassification;
import com.ietunnel.riskservice.significantRisk.service.CollapsePossibilityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道坍塌可能性评估指标分类 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/collapse-possibility-classification")
@CrossOrigin
public class CollapsePossibilityClassificationController {

    @Autowired
    private CollapsePossibilityClassificationService collapsePossibilityClassificationService;

    @PostMapping("/addCollapsePossibilityClassification")
    public R addCollapsePossibilityClassification(@RequestBody CollapsePossibilityClassification collapsePossibilityClassification){
        boolean save = collapsePossibilityClassificationService.save(collapsePossibilityClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllCollapsePossibilityClassification/{indicatorId}")
    public R findAllcasualtLevel(@PathVariable String indicatorId){
        QueryWrapper<CollapsePossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",indicatorId);
        List<CollapsePossibilityClassification> list = collapsePossibilityClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }


    @GetMapping("/findCollapsePossibilityClassificationByid/{id}")
    public R findCollapsePossibilityClassificationByid(@PathVariable String id){
        CollapsePossibilityClassification collapsePossibilityClassification = collapsePossibilityClassificationService.getById(id);
        return R.ok().data("CollapsePossibilityClassification",collapsePossibilityClassification);
    }

    @PostMapping("/updateCollapsePossibilityClassification")
    public R updateCollapsePossibilityClassification(@RequestBody CollapsePossibilityClassification collapsePossibilityClassification){
        boolean update = collapsePossibilityClassificationService.updateById(collapsePossibilityClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeCollapsePossibilityClassification/{id}")
    public R removeCollapsePossibilityClassification(@PathVariable String id){
        boolean remove = collapsePossibilityClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

