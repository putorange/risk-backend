package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.CasualtLevel;
import com.ietunnel.riskservice.levelGrading.entity.EconomyLossLevelCriteria;
import com.ietunnel.riskservice.levelGrading.service.CasualtLevelService;
import com.ietunnel.riskservice.levelGrading.service.EconomyLossLevelCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 直接经济损失等级标准 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/riskservice/levelGrading/economy-loss-level-criteria")
@CrossOrigin
public class EconomyLossLevelCriteriaController {


    @Autowired
    private EconomyLossLevelCriteriaService economyLossLevelCriteriaService;

    @PostMapping("/addEconomyLossLevel")
    public R addEconomyLossLevel(@RequestBody EconomyLossLevelCriteria economyLossLevelCriteria){
        boolean save = economyLossLevelCriteriaService.save(economyLossLevelCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllEconomyLossLevel/{current}/{limit}")
    public R findAllEconomyLossLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<EconomyLossLevelCriteria> page = new Page<>(current,limit);
        economyLossLevelCriteriaService.page(page,null);
        long total = page.getTotal();
        List<EconomyLossLevelCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findEconomyLossLevelByid/{id}")
    public R findEconomyLossLevelByid(@PathVariable String id){
        EconomyLossLevelCriteria economyLossLevelCriteria = economyLossLevelCriteriaService.getById(id);
        return R.ok().data("economyLossLevelCriteria",economyLossLevelCriteria);
    }

    @PostMapping("/updateEconomyLossLevel")
    public R updateEconomyLossLevel(@RequestBody EconomyLossLevelCriteria economyLossLevelCriteria){
        boolean update = economyLossLevelCriteriaService.updateById(economyLossLevelCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeEconomyLossLevel/{id}")
    public R removeEconomyLossLevel(@PathVariable String id){
        boolean remove = economyLossLevelCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

