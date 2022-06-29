package com.ietunnel.riskservice.levelGrading.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.levelGrading.entity.CasualtLevel;
import com.ietunnel.riskservice.levelGrading.entity.EventPossibilityLevel;
import com.ietunnel.riskservice.levelGrading.service.CasualtLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 人员伤亡等级标准 前端控制器
 * </p>
 *
 * @author wxb
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/riskservice/levelGrading/casualt-level")
@CrossOrigin
public class CasualtLevelController {

    @Autowired
    private CasualtLevelService casualtLevelService;

    @PostMapping("/addCasualtLevel")
    public R addlevel(@RequestBody CasualtLevel casualtLevel){
        boolean save = casualtLevelService.save(casualtLevel);
        if (save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @PostMapping("/findAllcasualtLevel/{current}/{limit}")
    public R findAllcasualtLevel(@PathVariable Long current, @PathVariable Long limit){
        Page<CasualtLevel> page = new Page<>(current,limit);
        casualtLevelService.page(page,null);
        long total = page.getTotal();
        List<CasualtLevel> records = page.getRecords();
        return R.ok().data("total",total).data("info",records);
    }

    @GetMapping("/findcasualtLevelByid/{id}")
    public R findcasualtLevelByid(@PathVariable String id){
        CasualtLevel casualtLevel = casualtLevelService.getById(id);
        return R.ok().data("casualtLevel",casualtLevel);
    }

    @PostMapping("/updatecasualtLevel")
    public R updatecasualtLevel(@RequestBody CasualtLevel casualtLevel){
        boolean update = casualtLevelService.updateById(casualtLevel);
        if (update){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @DeleteMapping("/removecasualtLevel/{id}")
    public R removecasualtLevel(@PathVariable String id){
        boolean remove = casualtLevelService.removeById(id);
        if (remove){
            return R.ok();
        }else {
            return R.error();
        }
    }


}

