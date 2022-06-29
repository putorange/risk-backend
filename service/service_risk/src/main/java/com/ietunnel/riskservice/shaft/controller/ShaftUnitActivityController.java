package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftUnitActivity;
import com.ietunnel.riskservice.shaft.service.ShaftUnitActivityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 竖井单位作业活动 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/riskservice/shaft/shaft-unit-activity")
@Api(description = "基础模块-竖井单位作业活动")
@CrossOrigin
public class ShaftUnitActivityController {

    @Autowired
    private ShaftUnitActivityService shaftUnitActivityService;

    @PostMapping("/addShaftUnitActivity")
    public R addlevel(@RequestBody ShaftUnitActivity shaftUnitActivity){
        boolean save = shaftUnitActivityService.save(shaftUnitActivity);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllShaftUnitActivity/{current}/{limit}")
    public R findAllShaftUnitActivity(@PathVariable Long current, @PathVariable Long limit){
        Page<ShaftUnitActivity> page = new Page<>(current,limit);
        shaftUnitActivityService.page(page,null);
        long total = page.getTotal();
        List<ShaftUnitActivity> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findShaftUnitActivityByid/{id}")
    public R findShaftUnitActivityById(@PathVariable String id){
        ShaftUnitActivity shaftUnitActivity = shaftUnitActivityService.getById(id);
        return R.ok().data("shaftUnitActivity",shaftUnitActivity);
    }

    @PostMapping("/updateShaftUnitActivity")
    public R updateShaftUnitActivity(@RequestBody ShaftUnitActivity shaftUnitActivity){
        boolean update = shaftUnitActivityService.updateById(shaftUnitActivity);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftUnitActivity/{id}")
    public R removeShaftUnitActivity(@PathVariable String id){
        boolean remove = shaftUnitActivityService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }


}




