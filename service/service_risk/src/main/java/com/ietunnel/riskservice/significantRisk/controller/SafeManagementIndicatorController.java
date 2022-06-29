package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.SafeManagementClassification;
import com.ietunnel.riskservice.significantRisk.entity.SafeManagementIndicator;
import com.ietunnel.riskservice.significantRisk.service.SafeManagementClassificationService;
import com.ietunnel.riskservice.significantRisk.service.SafeManagementIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 安全管理评估指标体系评估指标 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/riskservice/significantRisk/safe-management-indicator")
@CrossOrigin
public class SafeManagementIndicatorController {

    @Autowired
    private SafeManagementIndicatorService safeManagementIndicatorService;

    @Autowired
    private SafeManagementClassificationService safeManagementClassificationService;


    @PostMapping("/addSafeManagementIndicator")
    public R addSafeManagementIndicatorr(@RequestBody SafeManagementIndicator safeManagementIndicator){
        boolean save = safeManagementIndicatorService.save(safeManagementIndicator);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllSafeManagementIndicator")
    public R findAllSafeManagementIndicator(){
        List<SafeManagementIndicator> list = safeManagementIndicatorService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findSafeManagementIndicatorByid/{id}")
    public R findSafeManagementIndicatorByid(@PathVariable String id){
        SafeManagementIndicator safeManagementIndicator = safeManagementIndicatorService.getById(id);
        return R.ok().data("SafeManagementIndicator",safeManagementIndicator);
    }

    @PostMapping("/updateSafeManagementIndicator")
    public R updateSafeManagementIndicator(@RequestBody SafeManagementIndicator safeManagementIndicator){
        boolean update = safeManagementIndicatorService.updateById(safeManagementIndicator);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeSafeManagementIndicator/{id}")
    public R removeSafeManagementIndicator(@PathVariable String id){
        boolean remove = safeManagementIndicatorService.removeById(id);
        QueryWrapper<SafeManagementClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",id);
        List<SafeManagementClassification> list = safeManagementClassificationService.list(wrapper);
        for (SafeManagementClassification safeManagementClassification : list) {
            String id1 = safeManagementClassification.getId();
            safeManagementClassificationService.removeById(id1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

