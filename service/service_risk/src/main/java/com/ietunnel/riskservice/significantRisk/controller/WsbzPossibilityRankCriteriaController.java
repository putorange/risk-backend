package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.WsbzPossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.WsbzPossibilityRankCriteriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice.significantRisk/wsbz-possibility-rank-criteria")
@Api(description = "基础模块-瓦斯爆炸可能性风险等级")
@CrossOrigin
public class WsbzPossibilityRankCriteriaController {

    @Autowired
    private WsbzPossibilityRankCriteriaService wsbzPossibilityRankCriteriaService;

    @PostMapping("/addWsbzPossibilityRankCriteria")
    public R addWsbzPossibilityRankCriteria(@RequestBody WsbzPossibilityRankCriteria wsbzPossibilityRankCriteria){
        boolean save = wsbzPossibilityRankCriteriaService.save(wsbzPossibilityRankCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllWsbzPossibilityRankCriteria/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<WsbzPossibilityRankCriteria> page = new Page<>(current,limit);
        wsbzPossibilityRankCriteriaService.page(page,null);
        long total = page.getTotal();
        List<WsbzPossibilityRankCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findWsbzPossibilityRankCriteriaByid/{id}")
    public R findWsbzPossibilityRankCriteriaByid(@PathVariable String id){
        WsbzPossibilityRankCriteria wsbzPossibilityRankCriteria = wsbzPossibilityRankCriteriaService.getById(id);
        return R.ok().data("WsbzPossibilityRankCriteria",wsbzPossibilityRankCriteria);
    }

    @PostMapping("/updateWsbzPossibilityRankCriteria")
    public R updateWsbzPossibilityRankCriteria(@RequestBody WsbzPossibilityRankCriteria wsbzPossibilityRankCriteria){
        boolean update = wsbzPossibilityRankCriteriaService.updateById(wsbzPossibilityRankCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeWsbzPossibilityRankCriteria/{id}")
    public R removeWsbzPossibilityRankCriteria(@PathVariable String id){
        boolean remove = wsbzPossibilityRankCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}