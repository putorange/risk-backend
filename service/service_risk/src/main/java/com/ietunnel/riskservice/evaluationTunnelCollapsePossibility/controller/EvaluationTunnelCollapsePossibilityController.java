package com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Utils.toWord;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.EvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.Vo.ExportEvaluationTunnelCollapsePossibilityVo;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.entity.EvaluationTunnelCollapsePossibility;
import com.ietunnel.riskservice.evaluationTunnelCollapsePossibility.service.EvaluationTunnelCollapsePossibilityService;
import com.ietunnel.riskservice.significantRisk.entity.CollapsePossibilityRankCriteria;
import com.ietunnel.riskservice.significantRisk.service.CollapsePossibilityRankCriteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 隧道各区段坍塌事故可能性评估打分表 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-06-09
 */
@RestController
@RequestMapping("/riskservice/evaluationTunnelCollapsePossibility/evaluation-tunnel-collapse-possibility")
@CrossOrigin
public class EvaluationTunnelCollapsePossibilityController {

    @Autowired
    EvaluationTunnelCollapsePossibilityService evaluationTunnelCollapsePossibilityService;

    @Autowired
    CollapsePossibilityRankCriteriaService collapsePossibilityRankCriteriaService;

    @PostMapping("/getAllListBytunnelId/{tunelId}/{expertId}")
    public R getAllListBytunnelId(@PathVariable String tunelId,@PathVariable String expertId){
        List<EvaluationTunnelCollapsePossibilityVo> list = evaluationTunnelCollapsePossibilityService.getList(tunelId,expertId);
        return R.ok().data("list",list);
    }

    @GetMapping("/getcollapsePossibilityRankCriteria")
    public R getcollapsePossibilityRankCriteria(){
        List<CollapsePossibilityRankCriteria> list = collapsePossibilityRankCriteriaService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("/saveOrUpadateEvaluationTunnelCollapsePossibility")
    public R saveOrUpadateEvaluationTunnelCollapsePossibility(@RequestBody  EvaluationTunnelCollapsePossibility evaluationTunnelCollapsePossibility) {
        String id = evaluationTunnelCollapsePossibility.getId();
        if (id !=null && id.length()>0){
            boolean update = evaluationTunnelCollapsePossibilityService.updateById(evaluationTunnelCollapsePossibility);
            if (update){
                return R.ok();
            }else {
                return R.error();
            }
        }else {
            boolean save = evaluationTunnelCollapsePossibilityService.save(evaluationTunnelCollapsePossibility);
            if (save){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }

    @PostMapping("/removeId/{evaluationTunnelCollapsePossibilityId}")
    public R removeId(@PathVariable String evaluationTunnelCollapsePossibilityId)  {
        boolean b = evaluationTunnelCollapsePossibilityService.removeById(evaluationTunnelCollapsePossibilityId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @PostMapping("/expotHTML/{tunelId}/{expertId}")
    public R expotHTML(@PathVariable String tunelId,@PathVariable String expertId) throws IOException {
        List<ExportEvaluationTunnelCollapsePossibilityVo> list = evaluationTunnelCollapsePossibilityService.exportList(tunelId,expertId);

        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }
}

