package com.ietunnel.riskservice.specialRisk.controller;

import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.specialRisk.entity.EvaluationWorkEvent;
import com.ietunnel.riskservice.specialRisk.entity.vo.DivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.vo.SubDivisionWork;
import com.ietunnel.riskservice.specialRisk.entity.vo.WorkEventContent;
import com.ietunnel.riskservice.specialRisk.service.EvaluationWorkEventService;
import com.ietunnel.riskservice.specialRisk.service.TunnelDivisionWorkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 隧道施工作业活动与典型事故类型  按层级获取和更新数据
 * </p>
 *
 * @author zxg
 * @since 2022-04-19
 */

@RestController
@Api(description="评估-风险事件")
@RequestMapping("/riskservice/specialRisk/eval-work-event")
@CrossOrigin
public class EvaluationWorkEventController {
    @Autowired
    private TunnelDivisionWorkService tunnelDivisionWorkService;
    @Autowired
    private EvaluationWorkEventService evaluationWorkEventService;

    // 获取含子子项的DivisionWork的list
    @GetMapping("getAllWorkContent")
    public R getAllWorkContent(){
        List<DivisionWork> list = tunnelDivisionWorkService.getAllDivisionWork();
        return R.ok().data("list",list);
    }

    @ApiOperation("根据传过来的list更新作业事故（子子项）")
    @PostMapping("updateWorkContent")
    public R updateWorkContent(@RequestBody ArrayList<DivisionWork> list){
        boolean flag = false;
        for(DivisionWork divisionWork:list){
            //System.out.println(divisionWork.getName());
            for(SubDivisionWork subDivisionWork:divisionWork.getChildren()){
                //System.out.println(subDivisionWork.getName());
                for(WorkEventContent workEventContent:subDivisionWork.getChildren()){
                    //System.out.println(workEventContent.getName());
                    // workevent传值给evaluationworkevent数据表，更新数据
                    for(EvaluationWorkEvent evaluationWorkEvent:evaluationWorkEventService.list(null)){
                        if(workEventContent.getId().equals(evaluationWorkEvent.getUnitId())){
                            //BeanUtils.copyProperties(workEventContent,evaluationWorkEvent);
                            copyProperties(workEventContent,evaluationWorkEvent);
                            //evaluationWorkEvent.setId(String.valueOf(Math.random()*100).);
                            flag = evaluationWorkEventService.updateById(evaluationWorkEvent);
                        }

                    }
                }
            }
        }
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }
    private void copyProperties(WorkEventContent o1,EvaluationWorkEvent o2){
        o2.setUnitId(o1.getId());

        o2.setHit(o1.getHit());
        o2.setFall(o1.getFall());
        o2.setElectricity(o1.getElectricity());
        o2.setLift(o1.getLift());
        o2.setExplore(o1.getExplore());
        o2.setRoof(o1.getRoof());
        o2.setWater(o1.getWater());
        o2.setBlast(o1.getBlast());
        o2.setFire(o1.getFire());
        o2.setMechanical(o1.getMechanical());
        o2.setVehicle(o1.getVehicle());
        o2.setCollapse(o1.getCollapse());
        o2.setOthers(o1.getOthers());
    }

}
