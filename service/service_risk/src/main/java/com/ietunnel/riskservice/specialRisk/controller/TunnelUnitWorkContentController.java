package com.ietunnel.riskservice.specialRisk.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.TunnelDivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.TunnelUnitWorkContent;
import com.ietunnel.riskservice.specialRisk.service.TunnelUnitWorkContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riskservice/specialRisk/tunnel-unit-work-content")
@CrossOrigin
public class TunnelUnitWorkContentController {

    @Autowired
    private TunnelUnitWorkContentService tunnelUnitWorkContentService;

    @PostMapping("/addTunnelUnitWorkContent")
    public R addTunnelUnitWorkContent(@RequestBody TunnelUnitWorkContent tunnelUnitWorkContent){
        boolean save = tunnelUnitWorkContentService.save(tunnelUnitWorkContent);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllTunnelUnitWorkContent/{current}/{limit}")
    public R findAllTunnelUnitWorkContent(@PathVariable Long current, @PathVariable Long limit){
        Page<TunnelUnitWorkContent> page = new Page<>(current,limit);
        tunnelUnitWorkContentService.page(page,null);
        long total = page.getTotal();
        List<TunnelUnitWorkContent> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    // 本次补充的内容（2022/06/17 ↓↓↓↓↓）
    @GetMapping("/findAllTunnelUnitWorkContent")
    public R findAllTunnelUnitWorkContent(){
        List<TunnelUnitWorkContent> list = tunnelUnitWorkContentService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findTunnelUnitWorkContentByid/{id}")
    public R findTunnelUnitWorkContentByid(@PathVariable String id){
        TunnelUnitWorkContent tunnelUnitWorkContent = tunnelUnitWorkContentService.getById(id);
        return R.ok().data("TunnelUnitWorkContent",tunnelUnitWorkContent);
    }

    @PostMapping("/updateTunnelUnitWorkContent")
    public R updateTunnelUnitWorkContent(@RequestBody TunnelUnitWorkContent tunnelUnitWorkContent){
        boolean update = tunnelUnitWorkContentService.updateById(tunnelUnitWorkContent);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeTunnelUnitWorkContent/{id}")
    public R removeTunnelUnitWorkContent(@PathVariable String id){
        boolean remove = tunnelUnitWorkContentService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }


}