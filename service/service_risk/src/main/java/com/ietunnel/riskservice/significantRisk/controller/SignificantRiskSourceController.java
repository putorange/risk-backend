package com.ietunnel.riskservice.significantRisk.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskContent;
import com.ietunnel.riskservice.significantRisk.entity.SignificantRiskSource;
import com.ietunnel.riskservice.significantRisk.service.SignificantRiskContentService;
import com.ietunnel.riskservice.significantRisk.service.SignificantRiskSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 重大风险源 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-27
 */
@RestController
@RequestMapping("/riskservice/significantRisk/significant-risk-source")
@CrossOrigin
public class SignificantRiskSourceController {

    @Autowired
    private SignificantRiskSourceService significantRiskSourceService;

    @Autowired
    private SignificantRiskContentService significantRiskContentService;

    @PostMapping("/addSignificantRiskSource")
    public R addSignificantRiskSourcer(@RequestBody SignificantRiskSource significantRiskSource){
        boolean save = significantRiskSourceService.save(significantRiskSource);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllSignificantRiskSource")
    public R findAllSignificantRiskSource(){
        List<SignificantRiskSource> list = significantRiskSourceService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findSignificantRiskSourceByid/{id}")
    public R findSignificantRiskSourceByid(@PathVariable String id){
        SignificantRiskSource significantRiskSource = significantRiskSourceService.getById(id);
        return R.ok().data("SignificantRiskSource",significantRiskSource);
    }

    @PostMapping("/updateSignificantRiskSource")
    public R updateSignificantRiskSource(@RequestBody SignificantRiskSource significantRiskSource){
        boolean update = significantRiskSourceService.updateById(significantRiskSource);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeSignificantRiskSource/{id}")
    public R removeSignificantRiskSource(@PathVariable String id){
        boolean remove = significantRiskSourceService.removeById(id);
        QueryWrapper<SignificantRiskContent> wrapper = new QueryWrapper<>();
        wrapper.eq("significant_risk_source_id",id);
        List<SignificantRiskContent> list = significantRiskContentService.list(wrapper);
        for (SignificantRiskContent significantRiskContent : list) {
            String contentId = significantRiskContent.getId();
            significantRiskContentService.removeById(contentId);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

