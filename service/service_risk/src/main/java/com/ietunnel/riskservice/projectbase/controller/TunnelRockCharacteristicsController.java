package com.ietunnel.riskservice.projectbase.controller;

import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.TunnelRock;
import com.ietunnel.riskservice.projectbase.entity.TunnelRockCharacteristics;
import com.ietunnel.riskservice.projectbase.entity.vo.ExportTunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.service.TunnelRockCharacteristicsService;
import com.ietunnel.riskservice.projectbase.utils.ToWord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * <p>
 * 围岩级别与围岩特征表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/riskservice/projectbase/tunnel-rock-characteristics")
@Api(description = "基础模块-围岩级别与围岩特征表")
@CrossOrigin
public class TunnelRockCharacteristicsController {

    @Autowired
    public TunnelRockCharacteristicsService tunnelRockCharacteristicsService;

    @ApiOperation(value="查询隧道围岩信息")
    @GetMapping("/getTunnelRockInfo")
    public R getTunnelRockInfo(TunnelRockCharacteristicsVo tunnelRockCharacteristicsVo){
        List<TunnelRockCharacteristicsVo> tunnelRockList = this.tunnelRockCharacteristicsService.getTunnelRockInfo();

        return R.ok().data("tunnelRockList",tunnelRockList);
    }

    @ApiOperation(value="根据tunnel_id查询隧道围岩与特征")
    @GetMapping("getTunnelRockInfoById/{id}")
    public R getTunnelRockInfoById(@PathVariable String id) {
        TunnelRockCharacteristics tunnelRockInfo = tunnelRockCharacteristicsService.getById(id);
        return R.ok().data("tunnelRockInfo",tunnelRockInfo);
    }

    @PostMapping("/exportHTML/{tunnelId}")
    public R exportHTML(@PathVariable String tunnelId) throws IOException {
        List<ExportTunnelRockCharacteristicsVo> info = tunnelRockCharacteristicsService.exportList(tunnelId);

        ToWord toWord = new ToWord();
        CharSequence stringBuffer = toWord.toWord(info);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
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

