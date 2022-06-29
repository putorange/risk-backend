package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.RiskAcceptCriteria;
import com.ietunnel.riskservice.levelGrading.service.RiskAcceptCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 风险接受准则 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-15
 */
@RestController
@RequestMapping("/riskservice/levelGrading/risk-accept-criteria")
@CrossOrigin
public class RiskAcceptCriteriaController {
    @Autowired
    private RiskAcceptCriteriaService riskAcceptCriteriaService;

    @PostMapping("/addRiskAcceptCriteria")
    public R addlevel(@RequestBody RiskAcceptCriteria riskAcceptCriteria){
        boolean save = riskAcceptCriteriaService.save(riskAcceptCriteria);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllRiskAcceptCriteria/{current}/{limit}")
    public R findAllRiskAcceptCriteria(@PathVariable Long current, @PathVariable Long limit){
        Page<RiskAcceptCriteria> page = new Page<>(current,limit);
        riskAcceptCriteriaService.page(page,null);
        long total = page.getTotal();
        List<RiskAcceptCriteria> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findRiskAcceptCriteriaByid/{id}")
    public R findRiskAcceptCriteriaByid(@PathVariable String id){
        RiskAcceptCriteria riskAcceptCriteria = riskAcceptCriteriaService.getById(id);
        return R.ok().data("RiskAcceptCriteria",riskAcceptCriteria);
    }

    @PostMapping("/updateRiskAcceptCriteria")
    public R updateRiskAcceptCriteria(@RequestBody RiskAcceptCriteria riskAcceptCriteria){
        boolean update = riskAcceptCriteriaService.updateById(riskAcceptCriteria);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeRiskAcceptCriteria/{id}")
    public R removeRiskAcceptCriteria(@PathVariable String id){
        boolean remove = riskAcceptCriteriaService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

