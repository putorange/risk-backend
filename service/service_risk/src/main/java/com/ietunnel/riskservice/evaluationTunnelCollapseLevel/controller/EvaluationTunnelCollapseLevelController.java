package com.ietunnel.riskservice.evaluationTunnelCollapseLevel.controller;

import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.utils.toWord;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.EvaluationTunnelCollapseLevel;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.EvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.entity.vo.ExportEvaluationTunnelCollapseLevelVo;
import com.ietunnel.riskservice.evaluationTunnelCollapseLevel.service.EvaluationTunnelCollapseLevelService;
import com.ietunnel.riskservice.levelGrading.entity.RiskLevelCriteria;
import com.ietunnel.riskservice.levelGrading.service.RiskLevelCriteriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/riskservice.evaluationTunnelCollapseLevel/evaluation-tunnel-collapse-level")
@Api(description = "重大风险评估-隧道施工区段坍塌风险等级打分表")
@CrossOrigin
public class EvaluationTunnelCollapseLevelController {

    @Autowired
    private EvaluationTunnelCollapseLevelService evaluationTunnelCollapseLevelService;

    @Autowired
    private RiskLevelCriteriaService riskLevelCriteriaService;

    @PostMapping("/getAllListByTunnelId/{tunnelId}")
    public R getAllListByTunnelId(@PathVariable String tunnelId){
        List<EvaluationTunnelCollapseLevelVo> list = evaluationTunnelCollapseLevelService.getList(tunnelId);
        return R.ok().data("list",list);
    }

    @GetMapping("/getriskLevelCriteria")
    public R getriskLevelCriteria(){
        List<RiskLevelCriteria> list = riskLevelCriteriaService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("/saveOrUpadateEvaluationTunnelCollapseLevel")
    public R saveOrUpadateEvaluationTunnelCollapseLevel(@RequestBody EvaluationTunnelCollapseLevel evaluationTunnelCollapseLevel){
        String id = evaluationTunnelCollapseLevel.getId();
        if(id != null && id.length()>0){
            boolean update = evaluationTunnelCollapseLevelService.updateById(evaluationTunnelCollapseLevel);
            if (update){
                return R.ok();
            }else {
                return R.error();
            }
        }else {
            boolean save = evaluationTunnelCollapseLevelService.save(evaluationTunnelCollapseLevel);
            if (save){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }

    @PostMapping("/removeId/{evaluationTunnelCollapseLevelId}")
    public R removeById(@PathVariable String evaluationTunnelCollapseLevelId){
        boolean b = evaluationTunnelCollapseLevelService.removeById(evaluationTunnelCollapseLevelId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @PostMapping("/exportHTML/{tunnelId}")
    public R exportHTML(@PathVariable String tunnelId) throws IOException {

        List<ExportEvaluationTunnelCollapseLevelVo> list = evaluationTunnelCollapseLevelService.exportList(tunnelId);

        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }
}