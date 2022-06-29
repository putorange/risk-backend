package com.ietunnel.riskservice.evaluationTunnelRiskSource.controller;

import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.EvaluationRiskSourceTunnel;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.entity.Vo.ExportEvaluationTunnelRiskSourceVo;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.service.EvaluationRiskSourceTunnelService;
import com.ietunnel.riskservice.evaluationTunnelRiskSource.Utils.toWord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/riskservice.evaluationSpecialRisk/evaluation-risk-source-tunnel")
@Api(description = "专项风险评估-隧道风险源风险分析")
@CrossOrigin
public class EvaluationRiskSourceTunnelController {

    @Autowired
    private EvaluationRiskSourceTunnelService evaluationRiskSourceTunnelService;

    @GetMapping("/findAllByTunnelId/{tunnelId}")
    public R findAllEvaluationTunnelRiskSource(@PathVariable String tunnelId) {
        List<EvaluationRiskSourceTunnel> list = this.evaluationRiskSourceTunnelService.selectAll(tunnelId);
        return R.ok().data("list",list);
    }

    @GetMapping("/getEvaluationTunnelRiskSourceById/{id}")
    public R findAllEvaluationTunnelRiskSourceById(@PathVariable String id) {
        EvaluationRiskSourceTunnel evaluationRiskSourceTunnel = evaluationRiskSourceTunnelService.getById(id);
        return R.ok().data("info",evaluationRiskSourceTunnel);

    }

    @DeleteMapping("/removeEvaluationTunnelRiskSource/{id}")
    public R removeEvaluationTunnelRiskSource(@PathVariable String id) {
        boolean remove = evaluationRiskSourceTunnelService.removeById(id);
        if(remove) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/updateEvaluationTunnelRiskSource")
    public R updateEvaluationTunnelRiskSource(@RequestBody EvaluationRiskSourceTunnel evaluationTunnelRiskSource) {
        boolean flag = evaluationRiskSourceTunnelService.updateById(evaluationTunnelRiskSource);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/addEvaluationTunnelRiskSource")
    public R addEvaluationTunnelRiskSource(@RequestBody EvaluationRiskSourceTunnel evaluationTunnelRiskSource) {
        boolean save = evaluationRiskSourceTunnelService.save(evaluationTunnelRiskSource);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @PostMapping("/exportHTML/{tunnelId}")
    public R exportHTML(@PathVariable String tunnelId) throws IOException {
        List<ExportEvaluationTunnelRiskSourceVo> info = evaluationRiskSourceTunnelService.exportList(tunnelId);
        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(info);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }

}