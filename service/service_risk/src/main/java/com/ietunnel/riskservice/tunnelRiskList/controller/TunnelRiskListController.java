package com.ietunnel.riskservice.tunnelRiskList.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.service.TunnelRockCharacteristicsService;
import com.ietunnel.riskservice.tunnelRiskList.entity.TunnelRiskList;
import com.ietunnel.riskservice.tunnelRiskList.entity.vo.TunnelRiskListVo;
import com.ietunnel.riskservice.tunnelRiskList.service.TunnelRiskListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 隧道危险源普查清单表 前端控制器
 * </p>
 *
 * @author cmy
 * @since 2022-05-25
 */
@RestController
@RequestMapping("/riskservice/tunnelRiskList/tunnel-risk-list")
@Api(description = "评估模块-风险源普查清单")
@CrossOrigin
public class TunnelRiskListController {
    @Autowired
    public TunnelRiskListService tunnelRiskListService;

    @ApiOperation(value="查询风险源普查清单信息")
    @GetMapping("/getTunnelRiskInfo")
    public R getTunnelRiskInfo(TunnelRiskListVo tunnelRiskListVo){
        List<TunnelRiskListVo> tunnelRiskList = this.tunnelRiskListService.getTunnelRiskInfo();

        return R.ok().data("tunnelRiskList",tunnelRiskList);
    }

    @ApiOperation(value="根据division_id查询风险源普查清单信息")
    @GetMapping("getTunnelRiskInfoById/{id}")
    public R getTunnelRiskInfoById(@PathVariable String id) {
        TunnelRiskList tunnelRiskInfo = tunnelRiskListService.getById(id);
        return R.ok().data("tunnelRiskInfo",tunnelRiskInfo);
    }

    public static String listTojson(List<Map<String, Object>> list) {
        StringBuilder json = new StringBuilder();
        json.append("[");
        if (list != null && list.size() > 0) {
            for (Map<String, Object> map : list) {
                json.append(new JSONObject(map));
                json.append(",");
            }
            json.setCharAt(json.length() - 1, ']');
        } else {
            json.append("]");
        }
        return json.toString();
    }

}