package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftUnitWork;
import com.ietunnel.riskservice.shaft.service.ShaftUnitWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 竖井单位作业 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/riskservice/shaftWork/shaft-unit-work")
@CrossOrigin
public class ShaftUnitWorkController {

    @Autowired
    private ShaftUnitWorkService shaftUnitWorkService;


    @PostMapping("/addShaftUnitWork")
    public R addShaftUnitWork(@RequestBody ShaftUnitWork shaftUnitWork){
        boolean save = shaftUnitWorkService.save(shaftUnitWork);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllShaftUnitWork/{subdivisionId}")
    public R findAllShaftUnitWork(@PathVariable Long subdivisionId){
        QueryWrapper<ShaftUnitWork> wrapper = new QueryWrapper<>();
        wrapper.eq("subdivision_id",subdivisionId);
        List<ShaftUnitWork> list = shaftUnitWorkService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findShaftUnitWorkByid/{id}")
    public R findShaftUnitWorkByid(@PathVariable String id){
        ShaftUnitWork shaftUnitWork = shaftUnitWorkService.getById(id);
        return R.ok().data("ShaftUnitWork",shaftUnitWork);
    }

    @PostMapping("/updateShaftUnitWork")
    public R updateShaftUnitWork(@RequestBody ShaftUnitWork shaftUnitWork){
        boolean update = shaftUnitWorkService.updateById(shaftUnitWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftUnitWork/{id}")
    public R removeShaftUnitWork(@PathVariable String id){
        boolean remove = shaftUnitWorkService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

