package com.ietunnel.riskservice.specialRisk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.RiskEvent;
import com.ietunnel.riskservice.specialRisk.entity.UnsafeBehavior;
import com.ietunnel.riskservice.specialRisk.service.UnsafeBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/specialRisk/unsafe-behavior")
@CrossOrigin
public class UnsafeBehaviorController {

    @Autowired
    private UnsafeBehaviorService unsafeBehaviorService;

    @PostMapping("/addUnsafeBehavior")
    public R addUnsafeBehavior(@RequestBody UnsafeBehavior unsafeBehavior){
        boolean save = unsafeBehaviorService.save(unsafeBehavior);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllUnsafeBehavior/{current}/{limit}")
    public R findAllUnsafeBehavior(@PathVariable Long current, @PathVariable Long limit){
        Page<UnsafeBehavior> page = new Page<>(current,limit);
        unsafeBehaviorService.page(page,null);
        long total = page.getTotal();
        List<UnsafeBehavior> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findAllUnsafeBehavior")
    public R findAllUnsafeBehavior(){
        List<UnsafeBehavior> list = unsafeBehaviorService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findUnsafeBehaviorByid/{id}")
    public R findUnsafeBehaviorByid(@PathVariable String id){
        UnsafeBehavior unsafeBehavior = unsafeBehaviorService.getById(id);
        return R.ok().data("UnsafeBehavior",unsafeBehavior);
    }

    @PostMapping("/updateUnsafeBehavior")
    public R updateUnsafeBehavior(@RequestBody UnsafeBehavior unsafeBehavior){
        boolean update = unsafeBehaviorService.updateById(unsafeBehavior);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeUnsafeBehavior/{id}")
    public R removeUnsafeBehavior(@PathVariable String id){
        boolean remove = unsafeBehaviorService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}