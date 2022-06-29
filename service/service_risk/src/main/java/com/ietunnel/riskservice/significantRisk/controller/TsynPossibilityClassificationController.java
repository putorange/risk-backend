package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.TsynPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.service.TsynPossibilityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道突水涌泥可能性评估指标分类 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/tsyn-possibility-classification")
@CrossOrigin
public class TsynPossibilityClassificationController {

    @Autowired
    private TsynPossibilityClassificationService tsynPossibilityClassificationService;

    @PostMapping("/addTsynPossibilityClassification")
    public R addTsynPossibilityClassification(@RequestBody TsynPossibilityClassification tsynPossibilityClassification){
        boolean save = tsynPossibilityClassificationService.save(tsynPossibilityClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllTsynPossibilityClassification/{indicatorId}")
    public R findAllcasualtLevel(@PathVariable String indicatorId){
        QueryWrapper<TsynPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",indicatorId);
        List<TsynPossibilityClassification> list = tsynPossibilityClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }


    @GetMapping("/findTsynPossibilityClassificationByid/{id}")
    public R findTsynPossibilityClassificationByid(@PathVariable String id){
        TsynPossibilityClassification tsynPossibilityClassification = tsynPossibilityClassificationService.getById(id);
        return R.ok().data("TsynPossibilityClassification",tsynPossibilityClassification);
    }

    @PostMapping("/updateTsynPossibilityClassification")
    public R updateTsynPossibilityClassification(@RequestBody TsynPossibilityClassification tsynPossibilityClassification){
        boolean update = tsynPossibilityClassificationService.updateById(tsynPossibilityClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTsynPossibilityClassification/{id}")
    public R removeTsynPossibilityClassification(@PathVariable String id){
        boolean remove = tsynPossibilityClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

