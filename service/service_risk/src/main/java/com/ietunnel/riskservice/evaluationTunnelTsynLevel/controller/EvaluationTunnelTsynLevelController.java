package com.ietunnel.riskservice.evaluationTunnelTsynLevel.controller;

import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.Utils.toWord;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.EvaluationTunnelTsynLevel;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.EvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.entity.Vo.ExportEvaluationTunnelTsynLevelVo;
import com.ietunnel.riskservice.evaluationTunnelTsynLevel.service.EvaluationTunnelTsynLevelService;
import com.ietunnel.riskservice.levelGrading.entity.RiskLevelCriteria;
import com.ietunnel.riskservice.levelGrading.service.RiskLevelCriteriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/riskservice.evaluationTunnelTsynLevel/evaluation-tunnel-tsyn-level")
@Api(description = "重大风险评估-隧道施工区段突水涌泥风险等级打分表")
@CrossOrigin
public class EvaluationTunnelTsynLevelController {

    @Autowired
    private EvaluationTunnelTsynLevelService evaluationTunnelTsynLevelService;

    @Autowired
    private RiskLevelCriteriaService riskLevelCriteriaService;

    @PostMapping("/getAllListByTunnelId/{tunnelId}")
    public R getAllListByTunnelId(@PathVariable String tunnelId){
        List<EvaluationTunnelTsynLevelVo> list = evaluationTunnelTsynLevelService.getList(tunnelId);
        return R.ok().data("list",list);
    }

    @GetMapping("/getriskLevelCriteria")
    public R getriskLevelCriteria(){
        List<RiskLevelCriteria> list = riskLevelCriteriaService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("/saveOrUpadateEvaluationTunnelTsynLevel")
    public R saveOrUpadateEvaluationTunnelTsynLevel(@RequestBody EvaluationTunnelTsynLevel evaluationTunnelTsynLevel){
        String id = evaluationTunnelTsynLevel.getId();
        if(id != null && id.length()>0){
            boolean update = evaluationTunnelTsynLevelService.updateById(evaluationTunnelTsynLevel);
            if (update){
                return R.ok();
            }else {
                return R.error();
            }
        }else {
            boolean save = evaluationTunnelTsynLevelService.save(evaluationTunnelTsynLevel);
            if (save){
                return R.ok();
            }else {
                return R.error();
            }
        }
    }

    @DeleteMapping("/removeId/{evaluationTunnelTsynLevelId}")
    public R removeById(@PathVariable String evaluationTunnelTsynLevelId){
        boolean b = evaluationTunnelTsynLevelService.removeById(evaluationTunnelTsynLevelId);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @PostMapping("/exportHTML/{tunnelId}")
    public R exportHTML(@PathVariable String tunnelId) throws IOException {

        List<ExportEvaluationTunnelTsynLevelVo> list = evaluationTunnelTsynLevelService.exportList(tunnelId);

        toWord toWord = new toWord();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }
}