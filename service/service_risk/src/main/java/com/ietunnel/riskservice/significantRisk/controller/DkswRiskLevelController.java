package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.DkswRiskLevel;
import com.ietunnel.riskservice.significantRisk.service.DkswRiskLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 洞口失稳风险等级表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-02-09
 */
@RestController
@RequestMapping("/riskservice/significantRisk/dksw-risk-level")
@CrossOrigin
public class DkswRiskLevelController {

    @Autowired
    private DkswRiskLevelService dkswRiskLevelService;

    @PostMapping("/addDkswRiskLevel")
    public R addDkswRiskLevel(@RequestBody DkswRiskLevel dkswRiskLevel){
        boolean save = dkswRiskLevelService.save(dkswRiskLevel);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllDkswRiskLevel/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<DkswRiskLevel> page = new Page<>(current,limit);
        dkswRiskLevelService.page(page,null);
        long total = page.getTotal();
        List<DkswRiskLevel> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }


    @GetMapping("/findDkswRiskLevelByid/{id}")
    public R findDkswRiskLevelByid(@PathVariable String id){
        DkswRiskLevel dkswRiskLevel = dkswRiskLevelService.getById(id);
        return R.ok().data("DkswRiskLevel",dkswRiskLevel);
    }

    @PostMapping("/updateDkswRiskLevel")
    public R updateDkswRiskLevel(@RequestBody DkswRiskLevel dkswRiskLevel){
        boolean update = dkswRiskLevelService.updateById(dkswRiskLevel);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeDkswRiskLevel/{id}")
    public R removeDkswRiskLevel(@PathVariable String id){
        boolean remove = dkswRiskLevelService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

