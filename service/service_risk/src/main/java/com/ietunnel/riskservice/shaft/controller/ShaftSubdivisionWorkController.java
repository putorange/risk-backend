package com.ietunnel.riskservice.shaft.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftSubdivisionWork;
import com.ietunnel.riskservice.shaft.entity.ShaftUnitWork;
import com.ietunnel.riskservice.shaft.service.ShaftSubdivisionWorkService;
import com.ietunnel.riskservice.shaft.service.ShaftUnitWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/shaftWork/shaft-subdivision-work")
@CrossOrigin
public class ShaftSubdivisionWorkController {

    @Autowired
    private ShaftSubdivisionWorkService shaftSubdivisionWorkService;

    @Autowired
    private ShaftUnitWorkService shaftUnitWorkService;

    @PostMapping("/addShaftSubdivisionWork")
    public R addShaftSubdivisionWork(@RequestBody ShaftSubdivisionWork shaftSubdivisionWork){
        boolean save = shaftSubdivisionWorkService.save(shaftSubdivisionWork);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllShaftSubdivisionWork/{divisionId}")
    public R findAllShaftSubdivisionWork(@PathVariable Long divisionId){
        QueryWrapper<ShaftSubdivisionWork> wrapper = new QueryWrapper<>();
        wrapper.eq("division_id",divisionId);
        List<ShaftSubdivisionWork> list = shaftSubdivisionWorkService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findShaftSubdivisionWorkByid/{id}")
    public R findShaftSubdivisionWorkByid(@PathVariable String id){
        ShaftSubdivisionWork shaftSubdivisionWork = shaftSubdivisionWorkService.getById(id);
        return R.ok().data("ShaftSubdivisionWork",shaftSubdivisionWork);
    }

    @PostMapping("/updateShaftSubdivisionWork")
    public R updateShaftSubdivisionWork(@RequestBody ShaftSubdivisionWork shaftSubdivisionWork){
        boolean update = shaftSubdivisionWorkService.updateById(shaftSubdivisionWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftSubdivisionWork/{id}")
    public R removeShaftSubdivisionWork(@PathVariable String id){
        boolean remove = shaftSubdivisionWorkService.removeById(id);
        QueryWrapper<ShaftUnitWork> wrapper = new QueryWrapper<>();
        wrapper.eq("subdivision_id",id);
        List<ShaftUnitWork> list = shaftUnitWorkService.list(wrapper);
        for (ShaftUnitWork shaftUnitWork : list) {
            String unitWorkId = shaftUnitWork.getId();
            shaftUnitWorkService.removeById(unitWorkId);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}