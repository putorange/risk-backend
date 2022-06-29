package com.ietunnel.riskservice.specialRisk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.RiskEvent;
import com.ietunnel.riskservice.specialRisk.entity.RiskEvent;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWorkContent;
import com.ietunnel.riskservice.specialRisk.service.RiskEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/specialRisk/risk-event")
@CrossOrigin
public class RiskEventController {

    @Autowired
    private RiskEventService riskEventService;

    @PostMapping("/addRiskEvent")
    public R addRiskEvent(@RequestBody RiskEvent riskEvent){
        boolean save = riskEventService.save(riskEvent);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllRiskEvent/{current}/{limit}")
    public R findAllRiskEvent(@PathVariable Long current, @PathVariable Long limit){
        Page<RiskEvent> page = new Page<>(current,limit);
        riskEventService.page(page,null);
        long total = page.getTotal();
        List<RiskEvent> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }
    // 本次补充的内容（2022/06/17 ↓↓↓↓↓）
    @GetMapping("/findAllRiskEvent")
    public R findAllRiskEvent(){
        List<RiskEvent> list = riskEventService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findRiskEventByid/{id}")
    public R findRiskEventByid(@PathVariable String id){
        RiskEvent riskEvent = riskEventService.getById(id);
        return R.ok().data("RiskEvent",riskEvent);
    }

    @PostMapping("/updateRiskEvent")
    public R updateRiskEvent(@RequestBody RiskEvent riskEvent){
        boolean update = riskEventService.updateById(riskEvent);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeRiskEvent/{id}")
    public R removeRiskEvent(@PathVariable String id){
        boolean remove = riskEventService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}