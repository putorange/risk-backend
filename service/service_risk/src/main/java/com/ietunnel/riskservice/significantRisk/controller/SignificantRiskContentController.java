package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.RiskResourceCheck;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import com.ietunnel.riskservice.significantRisk.service.RiskResourceCheckService;
import com.ietunnel.riskservice.significantRisk.service.SignificantRiskContentService;
import com.ietunnel.riskservice.significantRisk.service.SignificantRiskSourceService;
import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 重大风险源内容 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/riskservice/significantRisk/significant-risk-content")
@CrossOrigin
public class SignificantRiskContentController {

    @Autowired
    private SignificantRiskContentService significantRiskContentService;
    @Autowired
    private SignificantRiskSourceService significantRiskSourceService;
    @Autowired
    private RiskResourceCheckService riskResourceCheckService;

    private void copyProperties(SignificantRiskContent riskContent, RiskResourceCheck riskResourceCheck){
        riskResourceCheck.setSignificantRiskContentId(riskContent.getId());
        riskResourceCheck.setSignificantRiskContentName(riskContent.getName());
        riskResourceCheck.setSignificantRiskSourceId(riskContent.getSignificantRiskSourceId());
        riskResourceCheck.setSignificantRiskSourceName((significantRiskSourceService.getById(riskContent.getSignificantRiskSourceId())).getName());
    }

    @PostMapping("/addSignificantRiskContent")
    public R addSignificantRiskContent(@RequestBody SignificantRiskContent significantRiskContent){
        boolean save = significantRiskContentService.save(significantRiskContent);
        RiskResourceCheck riskResourceCheck = new RiskResourceCheck();
        copyProperties(significantRiskContent,riskResourceCheck);
        riskResourceCheckService.save(riskResourceCheck);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllSignificantRiskContent/{significantRiskSourceId}")
    public R findAllSignificantRiskContent(@PathVariable Long significantRiskSourceId){
        QueryWrapper<SignificantRiskContent> wrapper = new QueryWrapper<>();
        wrapper.eq("significant_risk_source_id",significantRiskSourceId);
        List<SignificantRiskContent> list = significantRiskContentService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findSignificantRiskContentByid/{id}")
    public R findSignificantRiskContentByid(@PathVariable String id){
        SignificantRiskContent significantRiskContent = significantRiskContentService.getById(id);
        return R.ok().data("SignificantRiskContent",significantRiskContent);
    }

    @PostMapping("/updateSignificantRiskContent")
    public R updateSignificantRiskContent(@RequestBody SignificantRiskContent significantRiskContent){
        boolean update = significantRiskContentService.updateById(significantRiskContent);
        RiskResourceCheck riskResourceCheck = riskResourceCheckService.getByRiskContentId(significantRiskContent);
        riskResourceCheck.setSignificantRiskContentName(significantRiskContent.getName());
        riskResourceCheckService.updateById(riskResourceCheck);


        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeSignificantRiskContent/{id}")
    public R removeSignificantRiskContent(@PathVariable String id){
        SignificantRiskContent significantRiskContent = significantRiskContentService.getById(id);
        riskResourceCheckService.removeByRiskContentId(significantRiskContent.getId());
        boolean remove = significantRiskContentService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

