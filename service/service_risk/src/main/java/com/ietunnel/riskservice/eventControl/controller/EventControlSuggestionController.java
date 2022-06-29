package com.ietunnel.riskservice.eventControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.eventControl.entity.EventControlSubmeasure;
import com.ietunnel.riskservice.eventControl.entity.EventControlSuggestion;
import com.ietunnel.riskservice.eventControl.service.EventControlSuggestionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道事故控制措施建议表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-04-25
 */
@RestController
@RequestMapping("/riskservice/eventControl/event-control-suggestion")
@Api(description = "风险控制措施基础模块-风险控制建议")
@CrossOrigin
public class EventControlSuggestionController {
    
    @Autowired
    private EventControlSuggestionService eventControlSuggestionService;

    @PostMapping("/addEventControlSuggestion")
    public R addEventControlSuggestion(@RequestBody EventControlSuggestion eventControlSuggestion){
        boolean save = eventControlSuggestionService.save(eventControlSuggestion);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @GetMapping("/findAllEventControlSubmeasure/{submeasureId}")
    public R findAllEventControlSuggestion(@PathVariable Long submeasureId){
        QueryWrapper<EventControlSuggestion> wrapper = new QueryWrapper<>();
        wrapper.eq("submeasure_id",submeasureId);
        List<EventControlSuggestion> list = eventControlSuggestionService.list(wrapper);
        return R.ok().data("info",list);
    }

    @GetMapping("/findEventControlSuggestionByid/{id}")
    public R findEventControlSuggestionByid(@PathVariable String id){
        EventControlSuggestion eventControlSuggestion = eventControlSuggestionService.getById(id);
        return R.ok().data("eventControlSuggestion",eventControlSuggestion);
    }

    @PostMapping("/updateEventControlSuggestion")
    public R updateEventControlSuggestion(@RequestBody EventControlSuggestion eventControlSuggestion){
        boolean update = eventControlSuggestionService.updateById(eventControlSuggestion);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removeEventControlSuggestion/{id}")
    public R removeEventControlSuggestion(@PathVariable String id){
        boolean remove = eventControlSuggestionService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }
    

}

