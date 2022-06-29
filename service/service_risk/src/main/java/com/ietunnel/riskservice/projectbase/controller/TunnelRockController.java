package com.ietunnel.riskservice.projectbase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.TunnelRock;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.TunnelRockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道围岩表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/tunnelRock")
@Api(description = "基础模块-隧道竖井管理")
@CrossOrigin
public class TunnelRockController {

    @Autowired
    private TunnelRockService tunnelRockService;

    @ApiOperation(value="添加隧道围岩情况")
    @PostMapping("addTunnelRock")
    public R addTunnelRock(@RequestBody TunnelRock tunnelRock) {
        boolean save = tunnelRockService.save(tunnelRock);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有隧道围岩列表")
    @GetMapping("findAllTunnelRock")
    public R findAllTunnelRock() {
        //调用service的方法实现查询所有的操作
        List<TunnelRock> list = tunnelRockService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询隧道围岩")
    @GetMapping("getTunnelRock/{id}")
    public R getTunnelRock(@PathVariable String id) {
        TunnelRock tunnelRock = tunnelRockService.getById(id);
        return R.ok().data("tunnelRock",tunnelRock);
    }

    @ApiOperation(value = "逻辑删除隧道围岩")
    @DeleteMapping("removeTunnelRock/{id}")
    public R removeTunnelRock(@ApiParam(name = "id", value = "隧道围岩ID", required = true)
                         @PathVariable String id) {
        boolean flag = tunnelRockService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改隧道围岩信息")
    @PostMapping("updateTunnelRock")
    //@RequestBody:jason格式
    public R updateTunnelRock(@RequestBody TunnelRock tunnelRock) {
        boolean flag = tunnelRockService.updateById(tunnelRock);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageTunnelRockCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询隧道围岩")
    public R pageTunnelRockCondition(@PathVariable long current, @PathVariable long limit,
                                @RequestBody(required = false) IQuery tunnelRockQuery) {
        //创建page对象
        Page<TunnelRock> pageTunnelRock = new Page<>(current,limit);

        //构建条件
        QueryWrapper<TunnelRock> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String tunnelLineName = tunnelRockQuery.getTunnelLineName();
        Integer rockGrade = tunnelRockQuery.getRockGrade();
        String begin = tunnelRockQuery.getBegin();
        String end = tunnelRockQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(tunnelLineName)) {
            //构建条件
            wrapper.eq("tunnel_line_Name",tunnelLineName);
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
        tunnelRockService.page(pageTunnelRock,wrapper);

        long total = pageTunnelRock.getTotal();//总记录数
        List<TunnelRock> records = pageTunnelRock.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

