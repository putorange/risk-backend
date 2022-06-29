package com.ietunnel.riskservice.significantRisk.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.WsbzPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.entity.WsbzPossibilityIndicator;
import com.ietunnel.riskservice.significantRisk.service.WsbzPossibilityClassificationService;
import com.ietunnel.riskservice.significantRisk.service.WsbzPossibilityIndicatorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice.significantRisk/wsbz-possibility-indicator")
@Api(description = "基础模块-瓦斯爆炸可能性评估指标")
@CrossOrigin
public class WsbzPossibilityIndicatorController {

    @Autowired
    private WsbzPossibilityIndicatorService wsbzPossibilityIndicatorService;

    @Autowired
    private WsbzPossibilityClassificationService wsbzPossibilityClassificationService;

    @PostMapping("/addWsbzPossibilityIndicator")
    public R addWsbzPossibilityIndicator(@RequestBody WsbzPossibilityIndicator tsynPossibilityIndicator){
        boolean save = wsbzPossibilityIndicatorService.save(tsynPossibilityIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllWsbzPossibilityIndicator")
    public R findAllcasualtLevel(){
        List<WsbzPossibilityIndicator> list = wsbzPossibilityIndicatorService.list(null);
        return R.ok().data("info",list);
    }


    @GetMapping("/findWsbzPossibilityIndicatorByid/{id}")
    public R findWsbzPossibilityIndicatorByid(@PathVariable String id){
        WsbzPossibilityIndicator tsynPossibilityIndicator = wsbzPossibilityIndicatorService.getById(id);
        return R.ok().data("WsbzPossibilityIndicator",tsynPossibilityIndicator);
    }

    @PostMapping("/updateWsbzPossibilityIndicator")
    public R updateWsbzPossibilityIndicator(@RequestBody WsbzPossibilityIndicator tsynPossibilityIndicator){
        boolean update = wsbzPossibilityIndicatorService.updateById(tsynPossibilityIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeWsbzPossibilityIndicator/{id}")
    public R removeWsbzPossibilityIndicator(@PathVariable String id){
        boolean remove = wsbzPossibilityIndicatorService.removeById(id);
        QueryWrapper<WsbzPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",id);
        List<WsbzPossibilityClassification> list = wsbzPossibilityClassificationService.list(wrapper);
        for (WsbzPossibilityClassification tsynPossibilityClassification : list) {
            String id1 = tsynPossibilityClassification.getId();
            wsbzPossibilityClassificationService.removeById(id1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}