package com.ietunnel.riskservice.significantRisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.WsbzPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.service.WsbzPossibilityClassificationService;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice.significantRisk/wsbz-possibility-classification")
@Api(description = "基础模块-瓦斯爆炸可能性评估指标分类")
@CrossOrigin
public class WsbzPossibilityClassificationController {

    @Autowired
    private WsbzPossibilityClassificationService wsbzPossibilityClassificationService;

    @PostMapping("/addWsbzPossibilityClassification")
    public R addWsbzPossibilityClassification(@RequestBody WsbzPossibilityClassification wsbzPossibilityClassification){
        boolean save = wsbzPossibilityClassificationService.save(wsbzPossibilityClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllWsbzPossibilityClassification/{indicatorId}")
    public R findAllcasualtLevel(@PathVariable String indicatorId){
        QueryWrapper<WsbzPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",indicatorId);
        List<WsbzPossibilityClassification> list = wsbzPossibilityClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }


    @GetMapping("/findWsbzPossibilityClassificationByid/{id}")
    public R findWsbzPossibilityClassificationByid(@PathVariable String id){
        WsbzPossibilityClassification wsbzPossibilityClassification = wsbzPossibilityClassificationService.getById(id);
        return R.ok().data("WsbzPossibilityClassification",wsbzPossibilityClassification);
    }

    @PostMapping("/updateWsbzPossibilityClassification")
    public R updateWsbzPossibilityClassification(@RequestBody WsbzPossibilityClassification wsbzPossibilityClassification){
        boolean update = wsbzPossibilityClassificationService.updateById(wsbzPossibilityClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeWsbzPossibilityClassification/{id}")
    public R removeWsbzPossibilityClassification(@PathVariable String id){
        boolean remove = wsbzPossibilityClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}