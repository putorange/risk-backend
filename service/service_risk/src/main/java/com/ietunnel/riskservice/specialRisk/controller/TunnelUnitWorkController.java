package com.ietunnel.riskservice.specialRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWork;
import com.ietunnel.riskservice.specialRisk.service.EvaluationWorkEventService;
import com.ietunnel.riskservice.specialRisk.service.TunnelUnitWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道单位作业 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/specialRisk/tunnel-unit-work")
@CrossOrigin
public class TunnelUnitWorkController {

    @Autowired
    private TunnelUnitWorkService tunnelUnitWorkService;
    @Autowired
    private EvaluationWorkEventService evaluationWorkEventService;


    @GetMapping("/findAllTunnelUnitWork/{subdivisionId}")
    public R findAllTunnelUnitWork(@PathVariable Long subdivisionId){
        QueryWrapper<TunnelUnitWork> wrapper = new QueryWrapper<>();
        wrapper.eq("subdivision_id",subdivisionId);
        List<TunnelUnitWork> list = tunnelUnitWorkService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findTunnelUnitWorkByid/{id}")
    public R findTunnelUnitWorkByid(@PathVariable String id){
        TunnelUnitWork tunnelUnitWork = tunnelUnitWorkService.getById(id);
        return R.ok().data("TunnelUnitWork",tunnelUnitWork);
    }


    @PostMapping("/addTunnelUnitWork")
    public R addTunnelUnitWork(@RequestBody TunnelUnitWork  tunnelUnitWork){
        boolean save = tunnelUnitWorkService.save(tunnelUnitWork);
        // 同步操作EvaluationWorkEvent
        EvaluationWorkEvent workEvent = new EvaluationWorkEvent();
        copyProperties(tunnelUnitWork,workEvent);
        evaluationWorkEventService.save(workEvent);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/updateTunnelUnitWork")
    public R updateTunnelUnitWork(@RequestBody TunnelUnitWork tunnelUnitWork){
        boolean update = tunnelUnitWorkService.updateById(tunnelUnitWork);
        // 同步操作EvaluationWorkEvent
        EvaluationWorkEvent workEvent = evaluationWorkEventService.getByName(tunnelUnitWork.getName());
        evaluationWorkEventService.updateById(workEvent);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTunnelUnitWork/{id}")
    public R removeTunnelUnitWork(@PathVariable String id){
        TunnelUnitWork tunnelUnitWork = tunnelUnitWorkService.getById(id);
        EvaluationWorkEvent workEvent = evaluationWorkEventService.getByName(tunnelUnitWork.getName());
        evaluationWorkEventService.removeById(workEvent.getId());
        boolean remove = tunnelUnitWorkService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

    private void copyProperties(TunnelUnitWork unitWork,EvaluationWorkEvent workEvent){
        workEvent.setUnitId(unitWork.getId());
        workEvent.setSubdivisionId(unitWork.getSubdivisionId());
        workEvent.setName(unitWork.getName());
    }
}

