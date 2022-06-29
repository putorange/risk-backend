package com.ietunnel.riskservice.projectbase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.OneProject;
import com.ietunnel.riskservice.projectbase.entity.Segment;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.ProjectOfficeService;
import com.ietunnel.riskservice.projectbase.service.SegmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目办 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/riskservice/project-office")
@Api(description = "基础模块-项目办管理")
@CrossOrigin
public class ProjectOfficeController {

    @Autowired
    private SegmentService  segmentService;
    @Autowired
    private ProjectOfficeService projectOfficeService;

    @ApiOperation(value="管理公司添加项目办")
    @PostMapping("addManagementSubOffice")
    public R addManagementSubOffice(@RequestBody ProjectOffice projectOffice) {
        boolean save = projectOfficeService.save(projectOffice);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有项目办列表")
    @GetMapping("findAll")
    public R findAllProjectOffice() {
        //调用service的方法实现查询所有的操作
        List<ProjectOffice> list = projectOfficeService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询项目办")
    @GetMapping("getProjectOffice/{id}")
    public R getProjectOffice(@PathVariable String id) {
        ProjectOffice projectOffice = projectOfficeService.getById(id);
        return R.ok().data("projectOffice",projectOffice);
    }

    @ApiOperation(value = "根据id查询名下所有标段列表")
    @GetMapping("findAllSegment/{id}")
    public R findAllSegmentById(@PathVariable String id) {
        //调用service的方法实现查询所有的操作
        List<Segment> list = segmentService.list(new QueryWrapper<Segment>().eq("segment_id",id));
        return R.ok().data("items",list);
    }


    @ApiOperation(value = "逻辑删除项目办")
    @DeleteMapping("removeProjectOffice/{id}")
    public R removeProjectOffice(@ApiParam(name = "id", value = "项目办ID", required = true)
                              @PathVariable String id) {
        boolean flag = projectOfficeService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改项目办信息")
    @PostMapping("updateProjectOffice")
    //@RequestBody:jason格式
    public R updateProjectOffice(@RequestBody ProjectOffice projectOffice) {
        boolean flag = projectOfficeService.updateById(projectOffice);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageProjectOfficeCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询项目办")
    public R pageProjectOfficeCondition(@PathVariable long current, @PathVariable long limit,
                                     @RequestBody(required = false) IQuery officeQuery) {
        //创建page对象
        Page<ProjectOffice> pageOffice = new Page<>(current,limit);
        //构建条件
        QueryWrapper<ProjectOffice> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = officeQuery.getName();
        // json传递管理公司id
        String managementId = officeQuery.getManagementId();
        String begin = officeQuery.getBegin();
        String end = officeQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)) {
            //构建条件
            wrapper.like("name",name);
        }if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }
        // 根据管理公司id查询
        if(!StringUtils.isEmpty(managementId)){
            wrapper.eq("management_id",managementId);
        }

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        projectOfficeService.page(pageOffice,wrapper);

        long total = pageOffice.getTotal();//总记录数
        List<ProjectOffice> records = pageOffice.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 项目办分类列表(树形)
    @GetMapping("getAllProjectOffice")
    @ApiOperation(value = "项目管理公司和项目办的树形结构")
    public R getAllProjectOffice(){
        List<OneProject> list = projectOfficeService.getAllOneTowSubject();
        return R.ok().data("list",list);
    }
}

