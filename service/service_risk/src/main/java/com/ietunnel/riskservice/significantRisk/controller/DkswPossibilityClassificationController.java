package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.DkswPossibilityClassification;
import com.ietunnel.riskservice.significantRisk.service.DkswPossibilityClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道洞口失稳可能性评估指标分类 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/dksw-possibility-classification")
@CrossOrigin
public class DkswPossibilityClassificationController {

    @Autowired
    private DkswPossibilityClassificationService dkswPossibilityClassificationService;

    @PostMapping("/addDkswPossibilityClassification")
    public R addDkswPossibilityClassification(@RequestBody DkswPossibilityClassification dkswPossibilityClassification){
        boolean save = dkswPossibilityClassificationService.save(dkswPossibilityClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllDkswPossibilityClassification/{indicatorId}")
    public R findAllcasualtLevel(@PathVariable String indicatorId){
        QueryWrapper<DkswPossibilityClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",indicatorId);
        List<DkswPossibilityClassification> list = dkswPossibilityClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }


    @GetMapping("/findDkswPossibilityClassificationByid/{id}")
    public R findDkswPossibilityClassificationByid(@PathVariable String id){
        DkswPossibilityClassification dkswPossibilityClassification = dkswPossibilityClassificationService.getById(id);
        return R.ok().data("DkswPossibilityClassification",dkswPossibilityClassification);
    }

    @PostMapping("/updateDkswPossibilityClassification")
    public R updateDkswPossibilityClassification(@RequestBody DkswPossibilityClassification dkswPossibilityClassification){
        boolean update = dkswPossibilityClassificationService.updateById(dkswPossibilityClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeDkswPossibilityClassification/{id}")
    public R removeDkswPossibilityClassification(@PathVariable String id){
        boolean remove = dkswPossibilityClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

