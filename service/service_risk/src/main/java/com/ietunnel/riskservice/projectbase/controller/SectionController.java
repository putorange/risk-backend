package com.ietunnel.riskservice.projectbase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.ietunnel.riskservice.projectbase.entity.Section;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.SectionService;
import com.ietunnel.riskservice.tunnel.service.TunnelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 区段 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/riskservice/section")
@Api(description = "基础模块-区段管理")
@CrossOrigin
public class SectionController {

    //@Autowired
    private TunnelService tunnelService;
    @Autowired
    private SectionService sectionService;

    @ApiOperation(value="隧道添加区段")
    @PostMapping("addTunnelSubSection")
    public R addTunnelSubSection(@RequestBody Section section) {
        boolean save = sectionService.save(section);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有区段列表")
    @GetMapping("findAll")
    public R findAllSection() {
        //调用service的方法实现查询所有的操作
        List<Section> list = sectionService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询区段")
    @GetMapping("getSection/{id}")
    public R getSection(@PathVariable String id) {
        Section section = sectionService.getById(id);
        return R.ok().data("section",section);
    }

    @ApiOperation(value = "逻辑删除区段")
    @DeleteMapping("removeSection/{id}")
    public R removeSection(@ApiParam(name = "id", value = "区段ID", required = true)
                                 @PathVariable String id) {
        boolean flag = sectionService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改区段信息")
    @PostMapping("updateSection")
    //@RequestBody:jason格式
    public R updateSection(@RequestBody Section section) {
        boolean flag = sectionService.updateById(section);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageSectionCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询区段")
    public R pageSectionCondition(Section section,@PathVariable long current, @PathVariable long limit,
                                  @RequestBody(required = false) IQuery sectionQuery) {
        //创建page对象
        Page<Section> pageSection = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Section> wrapper = new QueryWrapper<>();
        wrapper.setEntity(section);
        // 多条件组合查询
        // mybatis学过 动态sql
        String tunnelLineName = sectionQuery.getTunnelLineName();
        Integer rockGrade = sectionQuery.getRockGrade();
        String begin = sectionQuery.getBegin();
        String end = sectionQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(tunnelLineName)) {
            //构建条件
            wrapper.eq("tunnel_line_name",tunnelLineName);
        }
        if(!StringUtils.isEmpty(rockGrade)) {
            wrapper.eq("rock_grade",rockGrade);
        }
        if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        sectionService.page(pageSection,wrapper);

        long total = pageSection.getTotal();//总记录数
        List<Section> records = pageSection.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
}

