package com.ietunnel.riskservice.specialRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
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
 * 隧道分项工程 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/specialRisk/tunnel-subdivision-work")
@CrossOrigin
public class TunnelSubdivisionWorkController {

    @Autowired
    private TunnelSubdivisionWorkService tunnelSubdivisionWorkService;

    @Autowired
    private TunnelUnitWorkService tunnelUnitWorkService;

    @PostMapping("/addTunnelSubdivisionWork")
    public R addTunnelSubdivisionWork(@RequestBody TunnelSubdivisionWork tunnelSubdivisionWork){
        boolean save = tunnelSubdivisionWorkService.save(tunnelSubdivisionWork);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllTunnelSubdivisionWork/{divisionId}")
    public R findAllTunnelSubdivisionWork(@PathVariable Long divisionId){
        QueryWrapper<TunnelSubdivisionWork> wrapper = new QueryWrapper<>();
        wrapper.eq("division_id",divisionId);
        List<TunnelSubdivisionWork> list = tunnelSubdivisionWorkService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findTunnelSubdivisionWorkByid/{id}")
    public R findTunnelSubdivisionWorkByid(@PathVariable String id){
        TunnelSubdivisionWork tunnelSubdivisionWork = tunnelSubdivisionWorkService.getById(id);
        return R.ok().data("TunnelSubdivisionWork",tunnelSubdivisionWork);
    }

    @PostMapping("/updateTunnelSubdivisionWork")
    public R updateTunnelSubdivisionWork(@RequestBody TunnelSubdivisionWork tunnelSubdivisionWork){
        boolean update = tunnelSubdivisionWorkService.updateById(tunnelSubdivisionWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTunnelSubdivisionWork/{id}")
    public R removeTunnelSubdivisionWork(@PathVariable String id){
        boolean remove = tunnelSubdivisionWorkService.removeById(id);
        QueryWrapper<TunnelUnitWork> wrapper = new QueryWrapper<>();
        wrapper.eq("subdivision_id",id);
        List<TunnelUnitWork> list = tunnelUnitWorkService.list(wrapper);
        for (TunnelUnitWork tunnelUnitWork : list) {
            String unitWorkId = tunnelUnitWork.getId();
            tunnelUnitWorkService.removeById(unitWorkId);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

