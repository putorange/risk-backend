package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.CCriteria;
import com.ietunnel.riskservice.levelGrading.service.CCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 暴露于危险环境的频繁程度(E) 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/riskservice/levelGrading/c-criteria")
@CrossOrigin
public class CCriteriaController {

    @Autowired
    private CCriteriaService cCriteriaService;

    @PostMapping("/addCCriteria")
    public R addCCriteria(@RequestBody CCriteria cCriteria ){
        boolean save = cCriteriaService.save(cCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllCCriteria/{current}/{limit}")
    public R findAllCCriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<CCriteria> page = new Page<>(current,limit);
        cCriteriaService.page(page,null);
        long total = page.getTotal();
        List<CCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findCCriteriaById/{id}")
    public R findCCriteriaById(@PathVariable String id){
        CCriteria cCriteria = cCriteriaService.getById(id);
        return R.ok().data("cCriteria",cCriteria);
    }

    @PostMapping("/updateCCriteria")
    public R updateCCriteria(@RequestBody CCriteria cCriteria){
        boolean update = cCriteriaService.updateById(cCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeCCriteria/{id}")
    public R removeCCriteria(@PathVariable String id){
        boolean remove = cCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

