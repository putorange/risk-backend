package com.ietunnel.riskservice.overallRiskAssessment.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.overallRiskAssessment.entity.OverallLevelCriteria;
import com.ietunnel.riskservice.overallRiskAssessment.service.OverallLevelCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 施工安全总体风险分级标准表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/overallRiskAssessment/overall-level-criteria")
@CrossOrigin
public class OverallLevelCriteriaController {
    @Autowired
    private OverallLevelCriteriaService overallLevelCriteriaService;

    @PostMapping("/addOverallLevelCriteria")
    public R addlevel(@RequestBody OverallLevelCriteria overallLevelCriteria){
        boolean save = overallLevelCriteriaService.save(overallLevelCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllOverallLevelCriteria/{current}/{limit}")
    public R findAllOverallLevelCriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<OverallLevelCriteria> page = new Page<>(current,limit);
        overallLevelCriteriaService.page(page,null);
        long total = page.getTotal();
        List<OverallLevelCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findOverallLevelCriteriaByid/{id}")
    public R findOverallLevelCriteriaByid(@PathVariable String id){
        OverallLevelCriteria overallLevelCriteria = overallLevelCriteriaService.getById(id);
        return R.ok().data("OverallLevelCriteria",overallLevelCriteria);
    }

    @PostMapping("/updateOverallLevelCriteria")
    public R updateOverallLevelCriteria(@RequestBody OverallLevelCriteria overallLevelCriteria){
        boolean update = overallLevelCriteriaService.updateById(overallLevelCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeOverallLevelCriteria/{id}")
    public R removeOverallLevelCriteria(@PathVariable String id){
        boolean remove = overallLevelCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

