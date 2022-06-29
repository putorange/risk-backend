package com.ietunnel.riskservice.tunnel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.Section;
import com.ietunnel.riskservice.projectbase.entity.TunnelRock;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.SectionService;
import com.ietunnel.riskservice.projectbase.service.TunnelRockService;
import com.ietunnel.riskservice.tunnel.entity.Tunnel;
import com.ietunnel.riskservice.tunnel.service.TunnelService;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 隧道表 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2022-01-11
 */
@RestController
@RequestMapping("/riskservice/tunnel/tunnel")
@Api(description = "基础模块-隧道管理")
@CrossOrigin
public class TunnelController {

    @Autowired
    private TunnelService tunnelService;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private TunnelRockService tunnelRockService;


    @ApiOperation(value="标段添加隧道")
    @PostMapping("addSegmentSubTunnel")
    public R addSegmentSubTunnel(@RequestBody Tunnel tunnel) {
        boolean save = tunnelService.save(tunnel);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有隧道列表")
    @GetMapping("findAll")
    public R findAllTunnel() {
        //调用service的方法实现查询所有的操作
        List<Tunnel> list = tunnelService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询隧道")
    @GetMapping("getTunnel/{id}")
    public R getTunnel(@PathVariable String id) {
        Tunnel tunnel = tunnelService.getById(id);
        return R.ok().data("tunnel",tunnel);
    }

    @ApiOperation(value = "根据id查询名下所有区段列表")
    @GetMapping("findAllSection/{id}")
    public R findAllSection(@PathVariable String id) {
        //调用service的方法实现查询所有的操作
        List<Section> list = sectionService.list(new QueryWrapper<Section>().eq("segment_id",id));
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "逻辑删除隧道")
    @DeleteMapping("removeTunnel/{id}")
    public R removeTunnel(@ApiParam(name = "id", value = "隧道ID", required = true)
                                 @PathVariable String id) {
        List<Section> sectionList = sectionService.list(new QueryWrapper<Section>().eq("tunnel_id",id));
        List<TunnelRock> tunnelRockList = tunnelRockService.list(new QueryWrapper<TunnelRock>().eq("tunnel_id",id));
        boolean flag = tunnelService.removeById(id);
        if(id != null && sectionList.size() == 0 && tunnelRockList.size() == 0 && flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改隧道信息")
    @PostMapping("updateTunnel")
    //@RequestBody:jason格式
    public R updateTunnel(@RequestBody Tunnel tunnel) {
        boolean flag = tunnelService.updateById(tunnel);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageTunnelCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询隧道")
    public R pageTunnelCondition(@PathVariable long current, @PathVariable long limit,
                                        @RequestBody(required = false) IQuery tunnelQuery) {
        //创建page对象
        Page<Tunnel> pageTunnel = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Tunnel> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = tunnelQuery.getName();
        String segmentId = tunnelQuery.getSegmentId();
        String begin = tunnelQuery.getBegin();
        String end = tunnelQuery.getEnd();
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
        if(!StringUtils.isEmpty(segmentId)){
            wrapper.eq("segment_id",segmentId);
        }

        //排序
        wrapper.orderByAsc("gmt_create");

        //调用方法实现条件查询分页
        tunnelService.page(pageTunnel,wrapper);

        long total = pageTunnel.getTotal();//总记录数
        List<Tunnel> records = pageTunnel.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }
}

