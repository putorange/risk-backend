package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.TsynPossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.TsynPossibilityRankCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道突水涌泥事故可能性等级标准表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/tsyn-possibility-rank-criteria")
@CrossOrigin
public class TsynPossibilityRankCriteriaController {

    @Autowired
    private TsynPossibilityRankCriteriaService tsynPossibilityRankCriteriaService;

    @PostMapping("/addTsynPossibilityRankCriteria")
    public R addTsynPossibilityRankCriteria(@RequestBody TsynPossibilityRankCriteria tsynPossibilityRankCriteria){
        boolean save = tsynPossibilityRankCriteriaService.save(tsynPossibilityRankCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllTsynPossibilityRankCriteria/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<TsynPossibilityRankCriteria> page = new Page<>(current,limit);
        tsynPossibilityRankCriteriaService.page(page,null);
        long total = page.getTotal();
        List<TsynPossibilityRankCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }


    @GetMapping("/findTsynPossibilityRankCriteriaByid/{id}")
    public R findTsynPossibilityRankCriteriaByid(@PathVariable String id){
        TsynPossibilityRankCriteria tsynPossibilityRankCriteria = tsynPossibilityRankCriteriaService.getById(id);
        return R.ok().data("TsynPossibilityRankCriteria",tsynPossibilityRankCriteria);
    }

    @PostMapping("/updateTsynPossibilityRankCriteria")
    public R updateTsynPossibilityRankCriteria(@RequestBody TsynPossibilityRankCriteria tsynPossibilityRankCriteria){
        boolean update = tsynPossibilityRankCriteriaService.updateById(tsynPossibilityRankCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTsynPossibilityRankCriteria/{id}")
    public R removeTsynPossibilityRankCriteria(@PathVariable String id){
        boolean remove = tsynPossibilityRankCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

