package com.ietunnel.riskservice.significantRisk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.DkswPossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.DkswPossibilityRankCriteriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice.significantRisk/dksw-possibility-rank-criteria")
@Api(description = "基础模块-隧道洞口失稳事故可能性等级标准表")
@CrossOrigin
public class DkswPossibilityRankCriteriaController {

    @Autowired
    private DkswPossibilityRankCriteriaService dkswPossibilityRankCriteriaService;

    @PostMapping("/addDkswPossibilityRankCriteria")
    public R addDkswPossibilityRankCriteria(@RequestBody DkswPossibilityRankCriteria dkswPossibilityRankCriteria){
        boolean save = dkswPossibilityRankCriteriaService.save(dkswPossibilityRankCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllDkswPossibilityRankCriteria/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<DkswPossibilityRankCriteria> page = new Page<>(current,limit);
        dkswPossibilityRankCriteriaService.page(page,null);
        long total = page.getTotal();
        List<DkswPossibilityRankCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }


    @GetMapping("/findDkswPossibilityRankCriteriaByid/{id}")
    public R findDkswPossibilityRankCriteriaByid(@PathVariable String id){
        DkswPossibilityRankCriteria dkswPossibilityRankCriteria = dkswPossibilityRankCriteriaService.getById(id);
        return R.ok().data("DkswPossibilityRankCriteria",dkswPossibilityRankCriteria);
    }

    @PostMapping("/updateDkswPossibilityRankCriteria")
    public R updateDkswPossibilityRankCriteria(@RequestBody DkswPossibilityRankCriteria dkswPossibilityRankCriteria){
        boolean update = dkswPossibilityRankCriteriaService.updateById(dkswPossibilityRankCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeDkswPossibilityRankCriteria/{id}")
    public R removeDkswPossibilityRankCriteria(@PathVariable String id){
        boolean remove = dkswPossibilityRankCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}


