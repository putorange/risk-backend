package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.CasualtLevel;
import com.ietunnel.riskservice.levelGrading.entity.LCriteria;
import com.ietunnel.riskservice.levelGrading.service.CasualtLevelService;
import com.ietunnel.riskservice.levelGrading.service.LCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 事故发生可能性L等级划分及赋值 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/riskservice/levelGrading/l-criteria")
@CrossOrigin
public class LCriteriaController {

    @Autowired
    private LCriteriaService lCriteriaService;

    @PostMapping("/addLCriteria")
    public R addLCriteria(@RequestBody LCriteria lCriteria ){
        boolean save = lCriteriaService.save(lCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllLCriteria/{current}/{limit}")
    public R findAllLCriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<LCriteria> page = new Page<>(current,limit);
        lCriteriaService.page(page,null);
        long total = page.getTotal();
        List<LCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findLCriteriaById/{id}")
    public R findLCriteriaById(@PathVariable String id){
        LCriteria lCriteria = lCriteriaService.getById(id);
        return R.ok().data("lCriteria",lCriteria);
    }

    @PostMapping("/updateLCriteria")
    public R updateLCriteria(@RequestBody LCriteria lCriteria){
        boolean update = lCriteriaService.updateById(lCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeLCriteria/{id}")
    public R removeLCriteria(@PathVariable String id){
        boolean remove = lCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

