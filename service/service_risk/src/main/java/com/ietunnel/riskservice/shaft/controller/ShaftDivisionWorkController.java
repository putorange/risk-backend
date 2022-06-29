package com.ietunnel.riskservice.shaft.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftDivisionWork;
import com.ietunnel.riskservice.shaft.entity.ShaftSubdivisionWork;
import com.ietunnel.riskservice.shaft.entity.ShaftUnitWork;
import com.ietunnel.riskservice.shaft.service.ShaftDivisionWorkService;
import com.ietunnel.riskservice.shaft.service.ShaftSubdivisionWorkService;
import com.ietunnel.riskservice.shaft.service.ShaftUnitWorkService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/shaftWork/shaft-division-work")
@Api(description = "竖井管理-分部工程")
@CrossOrigin
public class ShaftDivisionWorkController {

    @Autowired
    private ShaftDivisionWorkService shaftDivisionWorkService;
    @Autowired
    private ShaftSubdivisionWorkService shaftSubdivisionWorkService;
    @Autowired
    private ShaftUnitWorkService shaftUnitWorkService;

    @PostMapping("/addShaftDivisionWork")
    public R addShaftDivisionWork(@RequestBody ShaftDivisionWork shaftDivisionWork){
        boolean save = shaftDivisionWorkService.save(shaftDivisionWork);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @GetMapping("/findAllShaftDivisionWork")
    public R findAllShaftDivisionWork(){
        List<ShaftDivisionWork> list = shaftDivisionWorkService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findShaftDivisionWorkById/{id}")
    public R findShaftDivisionWorkByid(@PathVariable String id){
        ShaftDivisionWork shaftDivisionWork = shaftDivisionWorkService.getById(id);
        return R.ok().data("ShaftDivisionWork",shaftDivisionWork);
    }

    @PostMapping("/updateShaftDivisionWork")
    public R updateShaftDivisionWork(@RequestBody ShaftDivisionWork shaftDivisionWork){
        boolean update = shaftDivisionWorkService.updateById(shaftDivisionWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }
    @DeleteMapping("/removeShaftDivisionWork/{id}")
    public R removeShaftDivisionWork(@PathVariable String id){
        boolean remove = shaftDivisionWorkService.removeById(id);
        QueryWrapper<ShaftSubdivisionWork> wrapper = new QueryWrapper<>();
        wrapper.eq("division_id",id);
        List<ShaftSubdivisionWork> list = shaftSubdivisionWorkService.list(wrapper);
        for (ShaftSubdivisionWork shaftSubdivisionWork : list) {
            String subdivisionWorkId = shaftSubdivisionWork.getId();
            shaftSubdivisionWorkService.removeById(subdivisionWorkId);
            QueryWrapper<ShaftUnitWork> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("subdivision_id",subdivisionWorkId);
            shaftUnitWorkService.remove(wrapper1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}