package com.ietunnel.riskservice.specialRisk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.UnsafeBehavior;
import com.ietunnel.riskservice.specialRisk.entity.UnsafeState;
import com.ietunnel.riskservice.specialRisk.service.UnsafeStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/specialRisk/unsafe-state")
@CrossOrigin
public class UnsafeStateController {

    @Autowired
    private UnsafeStateService unsafeStateService;

    @PostMapping("/addUnsafeState")
    public R addUnsafeState(@RequestBody UnsafeState unsafeState){
        boolean save = unsafeStateService.save(unsafeState);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllUnsafeState/{current}/{limit}")
    public R findAllUnsafeState(@PathVariable Long current, @PathVariable Long limit){
        Page<UnsafeState> page = new Page<>(current,limit);
        unsafeStateService.page(page,null);
        long total = page.getTotal();
        List<UnsafeState> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findAllUnsafeState")
    public R findAllUnsafeStater(){
        List<UnsafeState> list = unsafeStateService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findUnsafeStateByid/{id}")
    public R findUnsafeStateByid(@PathVariable String id){
        UnsafeState unsafeState = unsafeStateService.getById(id);
        return R.ok().data("UnsafeState",unsafeState);
    }

    @PostMapping("/updateUnsafeState")
    public R updateUnsafeState(@RequestBody UnsafeState unsafeState){
        boolean update = unsafeStateService.updateById(unsafeState);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeUnsafeState/{id}")
    public R removeUnsafeState(@PathVariable String id){
        boolean remove = unsafeStateService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}