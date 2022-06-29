package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftPossibleAccident;
import com.ietunnel.riskservice.shaft.service.ShaftPossibleAccidentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 可能导致的事故（舒经单位作业活动） 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-04-14
 */
@RestController
@RequestMapping("/riskservice/shaft/shaft-possible-accident")
@Api(description = "基础模块-竖井可能导致的事故")
@CrossOrigin
public class ShaftPossibleAccidentController {

    @Autowired
    private ShaftPossibleAccidentService shaftPossibleAccidentService;

    @PostMapping("/addShaftPossibleAccident")
    public R addlevel(@RequestBody ShaftPossibleAccident shaftPossibleAccident){
        boolean save = shaftPossibleAccidentService.save(shaftPossibleAccident);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllShaftPossibleAccident/{current}/{limit}")
    public R findAllShaftPossibleAccident(@PathVariable Long current, @PathVariable Long limit){
        Page<ShaftPossibleAccident> page = new Page<>(current,limit);
        shaftPossibleAccidentService.page(page,null);
        long total = page.getTotal();
        List<ShaftPossibleAccident> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findShaftPossibleAccidentById/{id}")
    public R findShaftPossibleAccidentById(@PathVariable String id){
        ShaftPossibleAccident shaftPossibleAccident = shaftPossibleAccidentService.getById(id);
        return R.ok().data("shaftPossibleAccident",shaftPossibleAccident);
    }

    @PostMapping("/updateShaftPossibleAccident")
    public R updateShaftPossibleAccident(@RequestBody ShaftPossibleAccident shaftPossibleAccident){
        boolean update = shaftPossibleAccidentService.updateById(shaftPossibleAccident);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftPossibleAccident/{id}")
    public R removeShaftPossibleAccident(@PathVariable String id){
        boolean remove = shaftPossibleAccidentService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }


}




