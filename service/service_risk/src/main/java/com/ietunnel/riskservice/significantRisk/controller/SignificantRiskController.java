package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRisk;
import com.ietunnel.riskservice.significantRisk.service.SignificantRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 重大风险事故表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/riskservice/significantRisk/significant-risk")
@CrossOrigin
public class SignificantRiskController {

    @Autowired
    private SignificantRiskService significantRiskService;

    @PostMapping("/addSignificantRisk")
    public R addSignificantRisk(@RequestBody SignificantRisk significantRisk){
        boolean save = significantRiskService.save(significantRisk);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllSignificantRisk/{current}/{limit}")
    public R findAllSignificantRisk(@PathVariable Long current, @PathVariable Long limit){
        Page<SignificantRisk> page = new Page<>(current,limit);
        significantRiskService.page(page,null);
        long total = page.getTotal();
        List<SignificantRisk> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findSignificantRiskByid/{id}")
    public R findSignificantRiskByid(@PathVariable String id){
        SignificantRisk significantRisk = significantRiskService.getById(id);
        return R.ok().data("SignificantRisk",significantRisk);
    }

    @PostMapping("/updateSignificantRisk")
    public R updateSignificantRisk(@RequestBody SignificantRisk significantRisk){
        boolean update = significantRiskService.updateById(significantRisk);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeSignificantRisk/{id}")
    public R removeSignificantRisk(@PathVariable String id){
        boolean remove = significantRiskService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

