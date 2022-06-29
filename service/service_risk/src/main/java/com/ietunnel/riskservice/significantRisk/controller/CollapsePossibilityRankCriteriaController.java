package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.CollapsePossibilityRankCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道坍塌事故可能性等级标准表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/collapse-possibility-rank-criteria")
@CrossOrigin
public class CollapsePossibilityRankCriteriaController {

    @Autowired
    private CollapsePossibilityRankCriteriaService collapsePossibilityRankCriteriaService;

    @PostMapping("/addCollapsePossibilityRankCriteria")
    public R addCollapsePossibilityRankCriteria(@RequestBody CollapsePossibilityRankCriteria collapsePossibilityRankCriteria){
        boolean save = collapsePossibilityRankCriteriaService.save(collapsePossibilityRankCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllCollapsePossibilityRankCriteria/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<CollapsePossibilityRankCriteria> page = new Page<>(current,limit);
        collapsePossibilityRankCriteriaService.page(page,null);
        long total = page.getTotal();
        List<CollapsePossibilityRankCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }


    @GetMapping("/findCollapsePossibilityRankCriteriaByid/{id}")
    public R findCollapsePossibilityRankCriteriaByid(@PathVariable String id){
        CollapsePossibilityRankCriteria collapsePossibilityRankCriteria = collapsePossibilityRankCriteriaService.getById(id);
        return R.ok().data("CollapsePossibilityRankCriteria",collapsePossibilityRankCriteria);
    }

    @PostMapping("/updateCollapsePossibilityRankCriteria")
    public R updateCollapsePossibilityRankCriteria(@RequestBody CollapsePossibilityRankCriteria collapsePossibilityRankCriteria){
        boolean update = collapsePossibilityRankCriteriaService.updateById(collapsePossibilityRankCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeCollapsePossibilityRankCriteria/{id}")
    public R removeCollapsePossibilityRankCriteria(@PathVariable String id){
        boolean remove = collapsePossibilityRankCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

