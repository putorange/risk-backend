package com.ietunnel.riskservice.projectbase.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.TunnelRockProportion;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockCharacteristicsVo;
import com.ietunnel.riskservice.projectbase.entity.vo.TunnelRockProportionVo;
import com.ietunnel.riskservice.projectbase.service.TunnelRockProportionService;
import com.ietunnel.riskservice.projectbase.utils.ToWord1;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 隧道围岩4、5级所占比例 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-22
 */
@RestController
@RequestMapping("/riskservice/projectbase/tunnel-rock-proportion")
@CrossOrigin
public class TunnelRockProportionController {

    @Autowired
    TunnelRockProportionService tunnelRockProportionService;

    @ApiOperation(value = "隧道各围岩占比")
    @GetMapping("/getTunnelRockProportion")
    public R getTunnelRockProportion(TunnelRockCharacteristicsVo tunnelRockCharacteristicsVo){
        List<TunnelRockProportionVo> tunnelRockProportionList = this.tunnelRockProportionService.getTunnelRockPrpportion();
        return R.ok().data("tunnelRockProportion",tunnelRockProportionList);
    }

    @PostMapping("/exportHTML/{tunnelId}")
    public R exportHTML(@PathVariable String tunnelId) throws IOException {

        List<TunnelRockProportionVo> list = tunnelRockProportionService.exportList(tunnelId);

        ToWord1 toWord = new ToWord1();
        CharSequence stringBuffer = toWord.toWord(list);
        if (stringBuffer != null){
            return R.ok().data("info",stringBuffer);
        }else {
            return R.error();
        }
    }
}

