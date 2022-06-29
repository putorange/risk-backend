package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.shaft.entity.ShaftDangerFactor;
import com.ietunnel.riskservice.shaft.service.ShaftDangerFactorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/shaft/shaft-danger-factor")
@Api(description = "竖井危险因素")
@CrossOrigin
public class ShaftDangerFactorController {

    @Autowired
    private ShaftDangerFactorService shaftDangerFactorService;

    @PostMapping("/addShaftDangerFactor")
    public R addlevel(@RequestBody ShaftDangerFactor shaftDangerFactor){
        boolean save = shaftDangerFactorService.save(shaftDangerFactor);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllShaftDangerFactor/{current}/{limit}")
    public R findAllShaftDangerFactor(@PathVariable Long current, @PathVariable Long limit){
        Page<ShaftDangerFactor> page = new Page<>(current,limit);
        shaftDangerFactorService.page(page,null);
        long total = page.getTotal();
        List<ShaftDangerFactor> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findShaftDangerFactorById/{id}")
    public R findShaftDangerFactorById(@PathVariable String id){
        ShaftDangerFactor shaftDangerFactor = shaftDangerFactorService.getById(id);
        return R.ok().data("shaftDangerFactor",shaftDangerFactor);
    }

    @PostMapping("/updateShaftDangerFactor")
    public R updateShaftDangerFactor(@RequestBody ShaftDangerFactor shaftDangerFactor){
        boolean update = shaftDangerFactorService.updateById(shaftDangerFactor);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeShaftDangerFactor/{id}")
    public R removeShaftDangerFactor(@PathVariable String id){
        boolean remove = shaftDangerFactorService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

//    @Autowired
//    private ShaftDangerFactorService shaftDangerFactorService;
//
//    @Autowired
//    private ShaftPossibleAccidentService shaftPossibleAccidentService;
//
//    @PostMapping("/addShaftDangerFactor")
//    public R addShaftDangerFactor(@RequestBody ShaftDangerFactor shaftDangerFactor){
//        boolean save = shaftDangerFactorService.save(shaftDangerFactor);
//        if (save){
//            return R.ok();
//        }else {
//            return R.error();
//        }
//    }
//    @GetMapping("/findAllShaftDangerFactor")
//    public R findAllShaftDangerFactor(){
//        List<ShaftDangerFactor> list = shaftDangerFactorService.list(null);
//        return R.ok().data("info",list);
//    }
//    @GetMapping("/findAllShaftDangerFactor/{unitId}")
//    public R findAllShaftDangerFactor(@PathVariable Long unitId){
//        QueryWrapper<ShaftDangerFactor> wrapper = new QueryWrapper<>();
//        wrapper.eq("unit_id",unitId);
//        List<ShaftDangerFactor> list = shaftDangerFactorService.list(wrapper);
//        return R.ok().data("info",list);
//    }
//
//    @GetMapping("/findShaftDangerFactorByid/{id}")
//    public R findShaftDangerFactorByid(@PathVariable String id){
//        ShaftDangerFactor shaftDangerFactor = shaftDangerFactorService.getById(id);
//        return R.ok().data("ShaftDangerFactor",shaftDangerFactor);
//    }
//
//    @PostMapping("/updateShaftDangerFactor")
//    public R updateShaftDangerFactor(@RequestBody ShaftDangerFactor shaftDangerFactor){
//        boolean update = shaftDangerFactorService.updateById(shaftDangerFactor);
//        if (update){
//            return R.ok();
//        }else {
//            return R.error();
//        }
//    }
//
//    @DeleteMapping("/removeShaftDangerFactor/{id}")
//    public R removeShaftDangerFactor(@PathVariable String id){
//        boolean remove = shaftDangerFactorService.removeById(id);
//        QueryWrapper<ShaftPossibleAccident> wrapper = new QueryWrapper<>();
//        wrapper.eq("danger_id",id);
//        List<ShaftPossibleAccident> list = shaftPossibleAccidentService.list(wrapper);
//        for (ShaftPossibleAccident shaftPossibleAccident : list) {
//            String possibleAccidentId = shaftPossibleAccident.getId();
//            shaftPossibleAccidentService.removeById(possibleAccidentId);
//        }
//        if (remove){
//            return R.ok();
//        }else {
//            return R.error();
//        }
//    }

}
