package com.ietunnel.riskservice.evaluationManagement.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;

import com.ietunnel.riskservice.evaluationManagement.entity.EvaluationTunnelSafeManagement;
import com.ietunnel.riskservice.evaluationManagement.service.EvaluationTunnelSafeManagementService;
import com.ietunnel.riskservice.evaluationManagement.entity.vo.EvaluationTunnelSafeManagementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道安全管理评估指标打分表 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-05-22
 */

@RestController
@RequestMapping("/riskservice/evaluationManagement/evaluation-tunnel-safe-management")
@CrossOrigin
public class EvaluationTunnelSafeManagementController {
    @Autowired
    private EvaluationTunnelSafeManagementService evaluationTunnelSafeManagementService;


    @PostMapping("/getList/{current}/{limit}")
    public R getList(@PathVariable Long current, @PathVariable Long limit){
        Page<EvaluationTunnelSafeManagementVo> page = new Page<>(current, limit);
        evaluationTunnelSafeManagementService.query(page);
        List<EvaluationTunnelSafeManagementVo> records = page.getRecords();
        return R.ok().data("total",page.getTotal()).data("info",page.getRecords());
    }

    @PostMapping("/getAllList")
    public R getAllList(){
        List<EvaluationTunnelSafeManagementVo> list = evaluationTunnelSafeManagementService.queryall();
        return R.ok().data("total",list.size()).data("info",list);
    }

    @PostMapping("/addEvaluationTunnelSafeManagement")
    public R addSafeManagementClassification(@RequestBody EvaluationTunnelSafeManagement evaluationTunnelSafeManagement){
        boolean save = evaluationTunnelSafeManagementService.save(evaluationTunnelSafeManagement);
        System.out.println(evaluationTunnelSafeManagement);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }
}