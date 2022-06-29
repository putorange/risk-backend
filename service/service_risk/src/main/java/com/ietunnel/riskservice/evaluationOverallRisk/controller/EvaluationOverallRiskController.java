package com.ietunnel.riskservice.evaluationOverallRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationOverallRisk.Utils.toWord;
import com.ietunnel.riskservice.evaluationOverallRisk.entity.EvaluationOverallRisk;
import com.ietunnel.riskservice.evaluationOverallRisk.service.EvaluationOverallRiskService;
import com.ietunnel.riskservice.evaluationOverallRisk.service.ExportService;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EportEvalutionOverallRisk;
import com.ietunnel.riskservice.evaluationOverallRisk.vo.EvaluationOverallRiskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.sun.scenario.Settings.set;

/**
 * <p>
 * 总体风险评估指标体系打分表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/riskservice/evaluationOverallRisk/evaluation-overall-risk")
@CrossOrigin
public class EvaluationOverallRiskController {
    @Autowired
    private EvaluationOverallRiskService evaluationOverallRiskService;

    @Autowired
    private ExportService exportService;


    @PostMapping("/getAllListBytunnelId/{tunnelId}/{expertId}")
    public R getAllList(@PathVariable String tunnelId,@PathVariable String expertId){
        List<EvaluationOverallRiskVo> list = evaluationOverallRiskService.selectALLListBytunnelid(tunnelId,expertId);
        return R.ok().data("total",list.size()).data("info",list);
    }

    @PostMapping("/saveOrUpadateEvaluationOverallRisk")
    public R saveOrUpadateEvaluationOverallRisk(@RequestBody EvaluationOverallRisk evaluationOverallRisk) {
        String id = evaluationOverallRisk.getId();
        if (id !=null && id.length()>0){
            int update = evaluationOverallRiskService.updateByeId(evaluationOverallRisk);
            if (update == 1){
                return R.ok();
            }else {
                return R.error();
            }
        }else {
            boolean save = evaluationOverallRiskService.save(evaluationOverallRisk);
            if (save){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }

    @PostMapping("/expotHTML/{tunelId}/{expertId}")
    public R expotHTML(@PathVariable String tunelId,@PathVariable String expertId) throws IOException {
        List<EportEvalutionOverallRisk> list = exportService.selectALLList(tunelId,expertId);

        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }

    @PostMapping("/removeId/{evaluationId}")
    public R removeId(@PathVariable String evaluationId)  {
        int b = evaluationOverallRiskService.delId(evaluationId);
        return R.ok();
    }
}

