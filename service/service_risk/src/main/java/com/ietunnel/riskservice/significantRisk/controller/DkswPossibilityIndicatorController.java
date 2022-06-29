package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.DkswPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.entity.DkswPossibilityIndicator;
import com.ietunnel.riskservice.significantRisk.entity.SafeManagementIndicator;
import com.ietunnel.riskservice.significantRisk.service.DkswPossibilityClassificationService;
import com.ietunnel.riskservice.significantRisk.service.DkswPossibilityIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道洞口失稳可能性评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/dksw-possibility-indicator")
@CrossOrigin
public class DkswPossibilityIndicatorController {

    @Autowired
    private DkswPossibilityIndicatorService dkswPossibilityIndicatorService;

    @Autowired
    private DkswPossibilityClassificationService dkswPossibilityClassificationService;

    @PostMapping("/addDkswPossibilityIndicator")
    public R addDkswPossibilityIndicator(@RequestBody DkswPossibilityIndicator dkswPossibilityIndicator){
        boolean save = dkswPossibilityIndicatorService.save(dkswPossibilityIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllDkswPossibilityIndicator")
    public R findAllcasualtLevel(){
        List<DkswPossibilityIndicator> list = dkswPossibilityIndicatorService.list(null);
        return R.ok().data("info",list);
    }


    @GetMapping("/findDkswPossibilityIndicatorByid/{id}")
    public R findDkswPossibilityIndicatorByid(@PathVariable String id){
        DkswPossibilityIndicator dkswPossibilityIndicator = dkswPossibilityIndicatorService.getById(id);
        return R.ok().data("DkswPossibilityIndicator",dkswPossibilityIndicator);
    }

    @PostMapping("/updateDkswPossibilityIndicator")
    public R updateDkswPossibilityIndicator(@RequestBody DkswPossibilityIndicator dkswPossibilityIndicator){
        boolean update = dkswPossibilityIndicatorService.updateById(dkswPossibilityIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeDkswPossibilityIndicator/{id}")
    public R removeDkswPossibilityIndicator(@PathVariable String id){
        boolean remove = dkswPossibilityIndicatorService.removeById(id);
        QueryWrapper<DkswPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",id);
        List<DkswPossibilityClassification> list = dkswPossibilityClassificationService.list(wrapper);
        for (DkswPossibilityClassification dkswPossibilityClassification : list) {
            String id1 = dkswPossibilityClassification.getId();
            dkswPossibilityClassificationService.removeById(id1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

