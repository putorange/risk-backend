package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.EventPossibilityLevel;
import com.ietunnel.riskservice.levelGrading.service.EventPossibilityLevelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 事故发生可能性等级标准 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/riskservice/levelGrading/event-possibility-level")
@Api(description = "风险等级定级-风险事故可能性等级")
@CrossOrigin
public class EventPossibilityLevelController {

    @Autowired
    private EventPossibilityLevelService eventPossibilityLevelService;

//    @GetMapping("/findAll")
//    public R geteventpossibilitylevel() {
//        List<EventPossibilityLevel> list = eventPossibilityLevelService.list(null);
//        return R.ok().data("info",list);
//    }

    //分页
    @PostMapping("/findAllpossibilitylevel/{current}/{limit}")
    public R geteventpossibilitylevel(@PathVariable Long current,
                                        @PathVariable Long limit) {
        Page<EventPossibilityLevel> page = new Page<>(current, limit);
        eventPossibilityLevelService.page(page,null);
        long total = page.getTotal();
        List<EventPossibilityLevel> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findpossibilitylevelByid/{id}")
    public R geteventpossibilitylevelByid (@PathVariable String id){
        EventPossibilityLevel eventPossibilityLevel = eventPossibilityLevelService.getById(id);
//        QueryWrapper<EventPossibilityLevel> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        List<EventPossibilityLevel> list = eventPossibilityLevelService.list(queryWrapper);
        return R.ok().data("eventPossibilityLevel", eventPossibilityLevel);
    }

    @PostMapping("/updatepossibilitylevel")
    public R updateposasibilitylevel (@RequestBody EventPossibilityLevel eventPossibilityLevel){

        boolean update = eventPossibilityLevelService.updateById(eventPossibilityLevel);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/addpossibilitylevel")
    public R addlevel(@RequestBody EventPossibilityLevel eventPossibilityLevel){
        boolean save = eventPossibilityLevelService.save(eventPossibilityLevel);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removepossibilitylevel/{id}")
    public R removepossibilitylevel(@PathVariable String id){
        boolean remove = eventPossibilityLevelService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

