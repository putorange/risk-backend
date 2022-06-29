package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.SafeManagementClassification;
import com.ietunnel.riskservice.significantRisk.service.SafeManagementClassificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 安全管理评估指标分类 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/riskservice/significantRisk/safe-management-classification")
@CrossOrigin
public class SafeManagementClassificationController {

    @Autowired
    private SafeManagementClassificationService safeManagementClassificationService;


    @PostMapping("/addSafeManagementClassification")
    public R addSafeManagementClassificationr(@RequestBody SafeManagementClassification safeManagementClassification){
        boolean save = safeManagementClassificationService.save(safeManagementClassification);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllSafeManagementClassification/{indicatorId}")
    public R findAllSafeManagementClassification(@PathVariable Long indicatorId){
        QueryWrapper<SafeManagementClassification> wrapper = new QueryWrapper<>();
        wrapper.eq("indicator_id",indicatorId);
        List<SafeManagementClassification> list = safeManagementClassificationService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findSafeManagementClassificationByid/{id}")
    public R findSafeManagementClassificationByid(@PathVariable String id){
        SafeManagementClassification safeManagementClassification = safeManagementClassificationService.getById(id);
        return R.ok().data("SafeManagementClassification",safeManagementClassification);
    }

    @PostMapping("/updateSafeManagementClassification")
    public R updateSafeManagementClassification(@RequestBody SafeManagementClassification safeManagementClassification){
        boolean update = safeManagementClassificationService.updateById(safeManagementClassification);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeSafeManagementClassification/{id}")
    public R removeSafeManagementClassification(@PathVariable String id){
        boolean remove = safeManagementClassificationService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

