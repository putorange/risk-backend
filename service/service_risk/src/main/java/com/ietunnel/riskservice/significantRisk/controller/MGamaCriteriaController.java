package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.MGamaCriteria;
import com.ietunnel.riskservice.significantRisk.service.MGamaCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评估指标与折减系数对照表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-13
 */
@RestController
@RequestMapping("/riskservice/significantRisk/m-gama-criteria")
@CrossOrigin
public class MGamaCriteriaController {

    @Autowired
    private MGamaCriteriaService mGamaCriteriaService;

    @PostMapping("/addMGamaCriteria")
    public R addMGamaCriteriar(@RequestBody MGamaCriteria mGamaCriteria){
        boolean save = mGamaCriteriaService.save(mGamaCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllMGamaCriteria/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<MGamaCriteria> page = new Page<>(current,limit);
        mGamaCriteriaService.page(page,null);
        long total = page.getTotal();
        List<MGamaCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }


    @GetMapping("/findMGamaCriteriaByid/{id}")
    public R findMGamaCriteriaByid(@PathVariable String id){
        MGamaCriteria mGamaCriteria = mGamaCriteriaService.getById(id);
        return R.ok().data("MGamaCriteria",mGamaCriteria);
    }

    @PostMapping("/updateMGamaCriteria")
    public R updateMGamaCriteria(@RequestBody MGamaCriteria mGamaCriteria){
        boolean update = mGamaCriteriaService.updateById(mGamaCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeMGamaCriteria/{id}")
    public R removeMGamaCriteria(@PathVariable String id){
        boolean remove = mGamaCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

