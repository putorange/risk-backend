package com.ietunnel.riskservice.specialRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.TunnelDivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.TunnelSubdivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWork;
import com.ietunnel.riskservice.specialRisk.service.TunnelDivisionWorkService;
import com.ietunnel.riskservice.specialRisk.service.TunnelSubdivisionWorkService;
import com.ietunnel.riskservice.specialRisk.service.TunnelUnitWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道分部工程 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/specialRisk/tunnel-division-work")
@CrossOrigin
public class TunnelDivisionWorkController {

    @Autowired
    private TunnelDivisionWorkService tunnelDivisionWorkService;

    @Autowired
    private TunnelSubdivisionWorkService tunnelSubdivisionWorkService;

    @Autowired
    private TunnelUnitWorkService tunnelUnitWorkService;

    @PostMapping("/addTunnelDivisionWork")
    public R addTunnelDivisionWorkr(@RequestBody TunnelDivisionWork tunnelDivisionWork){
        boolean save = tunnelDivisionWorkService.save(tunnelDivisionWork);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllTunnelDivisionWork")
    public R findAllTunnelDivisionWork(){
        List<TunnelDivisionWork> list = tunnelDivisionWorkService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findTunnelDivisionWorkByid/{id}")
    public R findTunnelDivisionWorkByid(@PathVariable String id){
        TunnelDivisionWork tunnelDivisionWork = tunnelDivisionWorkService.getById(id);
        return R.ok().data("TunnelDivisionWork",tunnelDivisionWork);
    }

    @PostMapping("/updateTunnelDivisionWork")
    public R updateTunnelDivisionWork(@RequestBody TunnelDivisionWork tunnelDivisionWork){
        boolean update = tunnelDivisionWorkService.updateById(tunnelDivisionWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTunnelDivisionWork/{id}")
    public R removeTunnelDivisionWork(@PathVariable String id){
        boolean remove = tunnelDivisionWorkService.removeById(id);
        QueryWrapper<TunnelSubdivisionWork> wrapper = new QueryWrapper<>();
        wrapper.eq("division_id",id);
        List<TunnelSubdivisionWork> list = tunnelSubdivisionWorkService.list(wrapper);
        for (TunnelSubdivisionWork tunnelSubdivisionWork : list) {
            String subdivisionWorkId = tunnelSubdivisionWork.getId();
            tunnelSubdivisionWorkService.removeById(subdivisionWorkId);
            QueryWrapper<TunnelUnitWork> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("subdivision_id",subdivisionWorkId);
            tunnelUnitWorkService.remove(wrapper1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

