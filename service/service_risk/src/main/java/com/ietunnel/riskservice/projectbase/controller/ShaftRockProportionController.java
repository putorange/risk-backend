package com.ietunnel.riskservice.projectbase.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ShaftRockProportion;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.ShaftRockProportionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 竖井围岩占比表  前端控制器
 * </p>
 *
 * @author wxm
 * @since 2022-01-22
 */
@RestController
@RequestMapping("/riskservice/shaftRockProportion")
@Api(description = "基础模块-竖井围岩占比")
@CrossOrigin
public class ShaftRockProportionController {

    @Autowired
    private ShaftRockProportionService shaftRockProportionService;

    // @ApiOperation(value="隧道添加隧道围岩")
    // @PostMapping("addTunnelSubTunnel")
    // public R addTunnelSubTunnel(@RequestBody Tunnel Tunnel) {
    //     boolean save = TunnelService.save(Tunnel);
    //     if(save) {
    //         return R.ok();
    //     } else {
    //         return R.error();
    //     }
    // }

    @ApiOperation(value="添加隧道围岩占比")
    @PostMapping("addShaftRockProportion")
    public R addShaftRockProportion(@RequestBody ShaftRockProportion shaftRockProportion) {
        boolean save = shaftRockProportionService.save(shaftRockProportion);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有隧道围岩占比列表")
    @GetMapping("findAllShaftRockProportion")
    public R findAllShaftRockProportion() {
        //调用service的方法实现查询所有的操作
        List<ShaftRockProportion> list = shaftRockProportionService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询隧道围岩占比")
    @GetMapping("getShaftRockProportion/{id}")
    public R getShaftRockProportion(@PathVariable String id) {
        ShaftRockProportion shaftRockProportion = shaftRockProportionService.getById(id);
        return R.ok().data("shaftRockProportion",shaftRockProportion);
    }

    @ApiOperation(value = "逻辑删除隧道围岩占比")
    @DeleteMapping("removeShaftRockProportion/{id}")
    public R removeShaftRockProportion(@ApiParam(name = "id", value = "隧道围岩ID", required = true)
                                 @PathVariable String id) {
        boolean flag = shaftRockProportionService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改隧道围岩占比")
    @PostMapping("updateShaftRockProportion")
    //@RequestBody:jason格式
    public R updateShaftRockProportion(@RequestBody ShaftRockProportion shaftRockProportion) {
        boolean flag = shaftRockProportionService.updateById(shaftRockProportion);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageShaftRockProportionCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询隧道围岩占比")
    public R pageShaftRockProportionCondition(@PathVariable long current, @PathVariable long limit,
                                        @RequestBody(required = false) IQuery shaftRockProportionQuery) {
        //创建page对象
        Page<ShaftRockProportion> pageShaftRockProportion = new Page<>(current,limit);

        //构建条件
        QueryWrapper<ShaftRockProportion> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String liningType = shaftRockProportionQuery.getLiningType();
        String begin = shaftRockProportionQuery.getBegin();
        String end = shaftRockProportionQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(liningType)) {
            //构建条件
            wrapper.like("lining_type",liningType);
        }if(!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create",end);
        }

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        shaftRockProportionService.page(pageShaftRockProportion,wrapper);

        long total = pageShaftRockProportion.getTotal();//总记录数
        List<ShaftRockProportion> records = pageShaftRockProportion.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
}

