package com.ietunnel.riskservice.projectbase.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ProjectManagement;
import com.ietunnel.riskservice.projectbase.entity.ProjectOffice;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.ProjectManagementService;
import com.ietunnel.riskservice.projectbase.service.ProjectOfficeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目管理公司 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2021-12-21
 */
@RestController
@RequestMapping("/riskservice/project-management")
@Api(description="基础模块-项目管理公司")
@CrossOrigin
public class ProjectManagementController {

    @Autowired
    private ProjectManagementService projectManagementService;
    @Autowired
    private ProjectOfficeService projectOfficeService;

    //rest风格
    @ApiOperation(value="添加项目管理公司")
    @PostMapping("addManagement")
    public R addManagement(@RequestBody ProjectManagement projectManagement) {
        boolean save = projectManagementService.save(projectManagement);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有项目管理公司列表")
    @GetMapping("findAll")
    public R findAllProjectManagement() {
        //调用service的方法实现查询所有的操作
        List<ProjectManagement> list = projectManagementService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据id查询名下所有项目办列表")
    @GetMapping("findAllSubOffice/{id}")
    public R findAllManagementSubOfficeById(@PathVariable String id) {
        //调用service的方法实现查询所有的操作
        List<ProjectOffice> list = projectOfficeService.list(new QueryWrapper<ProjectOffice>().eq("management_id",id));
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询项目管理公司")
    @GetMapping("getManagement/{id}")
    public R getManagement(@PathVariable String id) {
        ProjectManagement projectManagement = projectManagementService.getById(id);
        return R.ok().data("projectManagement",projectManagement);
    }

    @ApiOperation(value = "逻辑删除项目管理公司")
    @DeleteMapping("removeManagement/{id}")
    public R removeManagement(@ApiParam(name = "id", value = "管理公司ID", required = true)
                            @PathVariable String id) {
        List<ProjectOffice> projectOfficeList = projectOfficeService.list(new QueryWrapper<ProjectOffice>().eq("management_id",id));
        boolean flag = projectManagementService.removeById(id);
        if (id != null && projectOfficeList.size() == 0 && flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改项目管理公司信息")
    @PostMapping("updateManagement")
    //@RequestBody:jason格式
    public R updateManagement(@RequestBody ProjectManagement projectManagement) {
        boolean flag = projectManagementService.updateById(projectManagement);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageManagementCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询项目管理公司")
    public R pageManagementCondition(@PathVariable long current, @PathVariable long limit,
                                   @RequestBody(required = false) IQuery managementQuery) {
        //创建page对象
        Page<ProjectManagement> pageManagement = new Page<>(current,limit);

        //构建条件
        QueryWrapper<ProjectManagement> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = managementQuery.getName();
        String begin = managementQuery.getBegin();
        String end = managementQuery.getEnd();
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

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        projectManagementService.page(pageManagement,wrapper);

        long total = pageManagement.getTotal();//总记录数
        List<ProjectManagement> records = pageManagement.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }


}

