package com.ietunnel.riskservice.eventControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.eventControl.entity.EventControlMeasure;
import com.ietunnel.riskservice.eventControl.entity.EventControlSubmeasure;
import com.ietunnel.riskservice.eventControl.entity.EventControlSuggestion;
import com.ietunnel.riskservice.eventControl.service.EventControlMeasureService;
import com.ietunnel.riskservice.eventControl.service.EventControlSubmeasureService;
import com.ietunnel.riskservice.eventControl.service.EventControlSuggestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道事故控制父级措施 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/riskservice/eventControl/event-control-measure")
@Api(description = "风险控制基础模块-风险控制措施")
@CrossOrigin
public class EventControlMeasureController {
    
    @Autowired
    private EventControlMeasureService eventControlMeasureService;

    @Autowired
    private EventControlSubmeasureService eventControlSubmeasureService;

    @Autowired
    private EventControlSuggestionService eventControlSuggestionService;

    @PostMapping("/addEventControlMeasure")
    public R addEventControlMeasure(@RequestBody EventControlMeasure eventControlMeasure){
        boolean save = eventControlMeasureService.save(eventControlMeasure);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllEventControlMeasure")
    public R findAllEventControlMeasure(){
        List<EventControlMeasure> list = eventControlMeasureService.list(null);
        return R.ok().data("info",list);
    }

    @GetMapping("/findEventControlMeasureByid/{id}")
    public R findEventControlMeasureByid(@PathVariable String id){
        EventControlMeasure tunnelDivisionWork = eventControlMeasureService.getById(id);
        return R.ok().data("eventControlMeasure",tunnelDivisionWork);
    }

    @PostMapping("/updateEventControlMeasure")
    public R updateEventControlMeasure(@RequestBody EventControlMeasure tunnelDivisionWork){
        boolean update = eventControlMeasureService.updateById(tunnelDivisionWork);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeEventControlMeasure/{id}")
    public R removeEventControlMeasure(@PathVariable String id){
        boolean remove = eventControlMeasureService.removeById(id);
        QueryWrapper<EventControlSubmeasure> wrapper = new QueryWrapper<>();
        wrapper.eq("measure_id",id);
        List<EventControlSubmeasure> list = eventControlSubmeasureService.list(wrapper);
        for (EventControlSubmeasure eventControlSubmeasure : list) {
            String submeasureId = eventControlSubmeasure.getId();
            eventControlSubmeasureService.removeById(submeasureId);
            QueryWrapper<EventControlSuggestion> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("submeasure_id",submeasureId);
            eventControlSuggestionService .remove(wrapper1);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }

    }

}

