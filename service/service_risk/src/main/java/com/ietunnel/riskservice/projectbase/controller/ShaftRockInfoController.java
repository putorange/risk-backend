package com.ietunnel.riskservice.projectbase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ShaftRockInfo;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.ShaftRockInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 竖井围岩情况表 前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/shaftRockInfo")
@Api(description = "基础模块-竖井围岩情况管理")
@CrossOrigin
public class ShaftRockInfoController {

    @Autowired
    private ShaftRockInfoService shaftRockInfoService;

    @ApiOperation(value="添加竖井围岩情况")
    @PostMapping("addShaftRockInfo")
    public R addShaftRockInfo(@RequestBody ShaftRockInfo shaftRockInfo) {
        boolean save = shaftRockInfoService.save(shaftRockInfo);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有竖井围岩情况列表")
    @GetMapping("findAllShaftRockInfo")
    public R findAllShaftRockInfo() {
        //调用service的方法实现查询所有的操作
        List<ShaftRockInfo> list = shaftRockInfoService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询竖井围岩情况")
    @GetMapping("getShaftRockInfo/{id}")
    public R getShaftRockInfo(@PathVariable String id) {
        ShaftRockInfo shaftRockInfo = shaftRockInfoService.getById(id);
        return R.ok().data("shaftRockInfo",shaftRockInfo);
    }

    @ApiOperation(value = "逻辑删除竖井围岩情况")
    @DeleteMapping("removeShaftRockInfo/{id}")
    public R removeShaftRockInfo(@ApiParam(name = "id", value = "竖井围岩情况ID", required = true)
                              @PathVariable String id) {
        boolean flag = shaftRockInfoService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改竖井围岩情况信息")
    @PostMapping("updateShaftRockInfo")
    //@RequestBody:jason格式
    public R updateShaftRockInfo(@RequestBody ShaftRockInfo shaftRockInfo) {
        boolean flag = shaftRockInfoService.updateById(shaftRockInfo);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageShaftRockInfoCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询竖井围岩情况")
    public R pageShaftRockInfoCondition(@PathVariable long current, @PathVariable long limit,
                                     @RequestBody(required = false)IQuery shaftRockInfoQuery) {
        //创建page对象
        Page<ShaftRockInfo> pageShaftRockInfo = new Page<>(current,limit);

        //构建条件
        QueryWrapper<ShaftRockInfo> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        Integer rockType = shaftRockInfoQuery.getRockType();
        String begin = shaftRockInfoQuery.getBegin();
        String end = shaftRockInfoQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(rockType)) {
            //构建条件
            wrapper.eq("rock_type",rockType);
        }if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        shaftRockInfoService.page(pageShaftRockInfo,wrapper);

        long total = pageShaftRockInfo.getTotal();//总记录数
        List<ShaftRockInfo> records = pageShaftRockInfo.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

