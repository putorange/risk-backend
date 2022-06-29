package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftWorkRisk;
import com.ietunnel.riskservice.shaft.service.ShaftWorkRiskService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 作业过程(表6.2-4) 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/riskservice/shaft/shaft-work-risk")
@Api(description = "竖井管理-竖井作业过程风险")
@CrossOrigin
public class ShaftWorkRiskController {

    @Autowired
    private ShaftWorkRiskService shaftWorkRiskService;

    @PostMapping("/addShaftWorkRisk")
    public R addlevel(@RequestBody ShaftWorkRisk shaftWorkRisk){
        boolean save = shaftWorkRiskService.save(shaftWorkRisk);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllShaftWorkRisk/{current}/{limit}")
    public R findAllShaftWorkRisk(@PathVariable Long current, @PathVariable Long limit){
        Page<ShaftWorkRisk> page = new Page<>(current,limit);
        shaftWorkRiskService.page(page,null);
        long total = page.getTotal();
        List<ShaftWorkRisk> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findShaftWorkRiskById/{id}")
    public R findShaftWorkRiskById(@PathVariable String id){
        ShaftWorkRisk shaftWorkRisk = shaftWorkRiskService.getById(id);
        return R.ok().data("shaftWorkRisk",shaftWorkRisk);
    }

    @PostMapping("/updateShaftWorkRisk")
    public R updateShaftWorkRisk(@RequestBody ShaftWorkRisk shaftWorkRisk){
        boolean update = shaftWorkRiskService.updateById(shaftWorkRisk);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftWorkRisk/{id}")
    public R removeShaftWorkRisk(@PathVariable String id){
        boolean remove = shaftWorkRiskService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

