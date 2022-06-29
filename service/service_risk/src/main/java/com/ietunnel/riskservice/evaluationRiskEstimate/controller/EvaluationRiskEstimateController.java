package com.ietunnel.riskservice.evaluationRiskEstimate.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationRiskEstimate.Utils.toWord;
import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.evaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.Vo.exportEvaluationRiskEstimateVo;
import com.ietunnel.riskservice.evaluationRiskEstimate.entity.EvaluationRiskEstimate;
import com.ietunnel.riskservice.evaluationRiskEstimate.service.EvaluationRiskEstimateService;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.EvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity.EvaluationTunnelCollapsePossibility;
import com.ietunnel.riskservice.levelGrading.entity.DCriteria;
import com.ietunnel.riskservice.levelGrading.service.DCriteriaService;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityRankCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 风险估测汇总表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-06-13
 */
@RestController
@RequestMapping("/riskservice/evaluationRiskEstimate/evaluation-risk-estimate")
@CrossOrigin
public class EvaluationRiskEstimateController {

    @Autowired
    EvaluationRiskEstimateService evaluationRiskEstimateService;

    @Autowired
    DCriteriaService dCriteriaService;

    @PostMapping("/getAllListBytunnelId/{tunelId}/{expertId}")
    public R getAllListBytunnelId(@PathVariable String tunelId,@PathVariable String expertId){
        List<evaluationRiskEstimateVo> list = evaluationRiskEstimateService.getevaluationRiskEstimateVo(tunelId,expertId);
        return R.ok().data("list",list);
    }

    @GetMapping("/getDCriteria")
    public R getDCriteria(){
        List<DCriteria> list = dCriteriaService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("/saveOrUpadateevaluationRiskEstimate")
    public R saveOrUpadateevaluationRiskEstimate(@RequestBody EvaluationRiskEstimate evaluationRiskEstimate) {
        String id = evaluationRiskEstimate.getId();
        if (id !=null && id.length()>0){
            boolean update = evaluationRiskEstimateService.updateById(evaluationRiskEstimate);
            if (update){
                return R.ok();
            }else {
                return R.error();
            }
        }else {
            boolean save = evaluationRiskEstimateService.save(evaluationRiskEstimate);
            if (save){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }

    @PostMapping("/removeId/{evaluationRiskEstimateId}")
    public R removeId(@PathVariable String evaluationRiskEstimateId)  {
        boolean b = evaluationRiskEstimateService.removeById(evaluationRiskEstimateId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/expotHTML/{tunelId}/{expertId}")
    public R expotHTML(@PathVariable String tunelId,@PathVariable String expertId) throws IOException {
        List<exportEvaluationRiskEstimateVo> list = evaluationRiskEstimateService.getExportList(tunelId, expertId);
        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }
}

