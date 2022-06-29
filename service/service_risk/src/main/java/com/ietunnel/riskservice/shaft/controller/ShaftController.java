package com.ietunnel.riskservice.shaft.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.ShaftRockInfo;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.ShaftRockInfoService;
import com.ietunnel.riskservice.shaft.entity.Shaft;
import com.ietunnel.riskservice.shaft.service.ShaftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 竖井表 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2022-01-23
 */
@RestController
@RequestMapping("/riskservice/shaft/shaft")
@Api(description = "基础模块-竖井管理")
@CrossOrigin
public class ShaftController {

    @Autowired
    private ShaftService shaftService;
    @Autowired
    private ShaftRockInfoService shaftRockInfoService;
    @Autowired
    private ShaftRockInfoService shaftRockProportionService;


    @ApiOperation(value="添加竖井")
    @PostMapping("addShaft")
    public R addShaft(@RequestBody Shaft shaft) {
        boolean save = shaftService.save(shaft);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有竖井列表")
    @GetMapping("findAll")
    public R findAllShaft() {
        //调用service的方法实现查询所有的操作
        List<Shaft> list = shaftService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询竖井")
    @GetMapping("getShaft/{id}")
    public R getShaft(@PathVariable String id) {
        Shaft shaft = shaftService.getById(id);
        return R.ok().data("shaft",shaft);
    }

    // @ApiOperation(value = "根据id查询名下所有竖井围岩列表")
    // @GetMapping("findAllShaftRock/{id}")
    // public R findAllShaftRock(@PathVariable String id) {
    //     //调用service的方法实现查询所有的操作
    //     List<ShaftRockInfo> list = shaftRockInfoService.list(new QueryWrapper<ShaftRockInfo>().eq("shaftRock_id",id));
    //     return R.ok().data("items",list);
    // }

    @ApiOperation(value = "逻辑删除竖井")
    @DeleteMapping("removeShaft/{id}")
    public R removeShaft(@ApiParam(name = "id", value = "竖井ID", required = true)
                          @PathVariable String id) {
        List<ShaftRockInfo> shaftRockInfoList = shaftRockInfoService.list(new QueryWrapper<ShaftRockInfo>().eq("shaft_id",id));
        List<ShaftRockInfo> shaftRockProportionList = shaftRockInfoService.list(new QueryWrapper<ShaftRockInfo>().eq("shaft_id",id));
        boolean flag = shaftService.removeById(id);
        if(id != null && shaftRockInfoList.size() == 0 && shaftRockProportionList.size() == 0 && flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改竖井信息")
    @PostMapping("updateShaft")
    //@RequestBody:jason格式
    public R updateShaft(@RequestBody Shaft shaft) {
        boolean flag = shaftService.updateById(shaft);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageShaftCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询竖井")
    public R pageShaftCondition(@PathVariable long current, @PathVariable long limit,
                                 @RequestBody(required = false) IQuery shaftQuery) {
        //创建page对象
        Page<Shaft> pageShaft = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Shaft> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String type = shaftQuery.getType();
        String begin = shaftQuery.getBegin();
        String end = shaftQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(type)) {
            //构建条件
            wrapper.eq("type",type);
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
        shaftService.page(pageShaft,wrapper);

        long total = pageShaft.getTotal();//总记录数
        List<Shaft> records = pageShaft.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

}

