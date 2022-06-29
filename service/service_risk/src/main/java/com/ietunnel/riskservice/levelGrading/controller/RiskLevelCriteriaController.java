package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.RiskLevelCriteria;
import com.ietunnel.riskservice.levelGrading.service.RiskLevelCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 风险等级标准表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/levelGrading/risk-level-criteria")
@CrossOrigin
public class RiskLevelCriteriaController {
    @Autowired
    private RiskLevelCriteriaService riskLevelCriteriaService;

    @PostMapping("/addRiskLevelCriteria")
    public R addlevel(@RequestBody RiskLevelCriteria riskLevelCriteria){
        boolean save = riskLevelCriteriaService.save(riskLevelCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllRiskLevelCriteria/{current}/{limit}")
    public R findAllRiskLevelCriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<RiskLevelCriteria> page = new Page<>(current,limit);
        riskLevelCriteriaService.page(page,null);
        long total = page.getTotal();
        List<RiskLevelCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findRiskLevelCriteriaByid/{id}")
    public R findRiskLevelCriteriaByid(@PathVariable String id){
        RiskLevelCriteria riskLevelCriteria = riskLevelCriteriaService.getById(id);
        return R.ok().data("RiskLevelCriteria",riskLevelCriteria);
    }

    @PostMapping("/updateRiskLevelCriteria")
    public R updateRiskLevelCriteria(@RequestBody RiskLevelCriteria riskLevelCriteria){
        boolean update = riskLevelCriteriaService.updateById(riskLevelCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeRiskLevelCriteria/{id}")
    public R removeRiskLevelCriteria(@PathVariable String id){
        boolean remove = riskLevelCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

