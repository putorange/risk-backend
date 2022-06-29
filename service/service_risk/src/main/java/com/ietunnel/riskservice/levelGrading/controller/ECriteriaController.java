package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.ECriteria;
import com.ietunnel.riskservice.levelGrading.service.ECriteriaService;
import com.ietunnel.riskservice.levelGrading.service.ECriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人员暴露时间E等级划分及赋值 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/riskservice/levelGrading/e-criteria")
@CrossOrigin
public class ECriteriaController {

    @Autowired
    private ECriteriaService eCriteriaService;

    @PostMapping("/addECriteria")
    public R addECriteria(@RequestBody ECriteria eCriteria ){
        boolean save = eCriteriaService.save(eCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllECriteria/{current}/{limit}")
    public R findAllECriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<ECriteria> page = new Page<>(current,limit);
        eCriteriaService.page(page,null);
        long total = page.getTotal();
        List<ECriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findECriteriaById/{id}")
    public R findECriteriaById(@PathVariable String id){
        ECriteria eCriteria = eCriteriaService.getById(id);
        return R.ok().data("eCriteria",eCriteria);
    }

    @PostMapping("/updateECriteria")
    public R updateECriteria(@RequestBody ECriteria eCriteria){
        boolean update = eCriteriaService.updateById(eCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeECriteria/{id}")
    public R removeECriteria(@PathVariable String id){
        boolean remove = eCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

