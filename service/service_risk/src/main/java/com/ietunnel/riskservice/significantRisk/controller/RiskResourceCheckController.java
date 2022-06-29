package com.ietunnel.riskservice.significantRisk.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.significantRisk.entity.RiskResourceCheck;
import com.ietunnel.riskservice.significantRisk.service.RiskResourceCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道风险源检查表 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2022-06-07
 */
@RestController
@Api(description="评估-风险源检查")
@RequestMapping("/riskservice.significantRisk/risk-resource-check")
//@CrossOrigin(methods = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin(origins = "*")
public class RiskResourceCheckController {
    @Autowired
    private RiskResourceCheckService riskResourceCheckService;

    @ApiOperation("获取所有RiskResourceCheck数据")
    @GetMapping("getAllRiskResourceCheck")
    public R getAllWorkContent(){
        // 方式1：多表连接查询出来的数据
//        List<RiskResourceCheckTable> list = riskResourceCheckService.getAllRiskResourceCheck();

        // 方式2：直接从risk_resource_check表中获取数据
//        List<RiskResourceCheck> list = riskResourceCheckService.list(null);

        // 方式3：根据风险源id顺利查询风险源检查表中所有的数据，使后添加的数据排到list中的兄弟数据旁
        List<RiskResourceCheck> list = riskResourceCheckService.getAllRiskResourceCheckByResourceId();
        return R.ok().data("list",list);
    }
    @ApiOperation("更新RiskResourceCheck数据")
    @PostMapping("updateRiskResourceCheck")
    public R updateRiskResourceCheck(@RequestBody List<RiskResourceCheck> list){
        boolean flag = false;
        // riskResourceCheck：前端返回的数据，riskResourceCheck1数据库中的数据（待更新的数据）
        for(RiskResourceCheck riskResourceCheck : list){
            for(RiskResourceCheck riskResourceCheck1 : riskResourceCheckService.list(null)){
                if(riskResourceCheck1.getId().equals(riskResourceCheck.getId())){
                    copyProperties(riskResourceCheck,riskResourceCheck1);
                    flag = riskResourceCheckService.updateById(riskResourceCheck);
                }
            }
        }
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    private void copyProperties(RiskResourceCheck o1,RiskResourceCheck o2){
        o2.setCollapse(o1.getCollapse());
        o2.setHoleInstability(o1.getHoleInstability());
        o2.setWaterInrush(o1.getWaterInrush());

    }



}

