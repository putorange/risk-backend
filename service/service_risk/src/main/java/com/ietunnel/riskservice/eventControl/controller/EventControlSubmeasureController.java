package com.ietunnel.riskservice.eventControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.eventControl.entity.EventControlSubmeasure;
import com.ietunnel.riskservice.eventControl.entity.EventControlSuggestion;
import com.ietunnel.riskservice.eventControl.service.EventControlSubmeasureService;
import com.ietunnel.riskservice.eventControl.service.EventControlSuggestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道事故控制子级措施 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/riskservice/eventControl/event-control-submeasure")
@Api(description = "风险控制措施基础模块-风险子措施")
@CrossOrigin
public class EventControlSubmeasureController {

    @Autowired
    private EventControlSubmeasureService eventControlSubmeasureService;

    @Autowired
    private EventControlSuggestionService eventControlSuggestionService;

    @PostMapping("/addEventControlSubmeasure")
    public R addEventControlSubmeasure(@RequestBody EventControlSubmeasure eventControlSubmeasure){
        boolean save = eventControlSubmeasureService.save(eventControlSubmeasure);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllEventControlSubmeasure/{measureId}")
    public R findAllEventControlSubmeasure(@PathVariable Long measureId){
        QueryWrapper<EventControlSubmeasure> wrapper = new QueryWrapper<>();
        wrapper.eq("measure_id",measureId);
        List<EventControlSubmeasure> list = eventControlSubmeasureService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findEventControlSubmeasureByid/{id}")
    public R findEventControlSubmeasureByid(@PathVariable String id){
        EventControlSubmeasure eventControlSubmeasure = eventControlSubmeasureService.getById(id);
        return R.ok().data("eventControlSubmeasure",eventControlSubmeasure);
    }

    @PostMapping("/updateEventControlSubmeasure")
    public R updateEventControlSubmeasure(@RequestBody EventControlSubmeasure eventControlSubmeasure){
        boolean update = eventControlSubmeasureService.updateById(eventControlSubmeasure);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeEventControlSubmeasure/{id}")
    public R removeEventControlSubmeasure(@PathVariable String id){
        boolean remove = eventControlSubmeasureService.removeById(id);
        QueryWrapper<EventControlSuggestion> wrapper = new QueryWrapper<>();
        wrapper.eq("submeasure_id",id);
        List<EventControlSuggestion> list = eventControlSuggestionService.list(wrapper);
        for (EventControlSuggestion eventControlSuggestion : list) {
            String suggestionId = eventControlSuggestion.getId();
            eventControlSuggestionService.removeById(suggestionId);
        }
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

