package com.ietunnel.riskservice.projectbase.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.projectbase.entity.OfficeSegmentClass.OneSegment;
import com.ietunnel.riskservice.projectbase.entity.ProjectManagement;
import com.ietunnel.riskservice.projectbase.entity.ProjectOfficeClass.OneProject;
import com.ietunnel.riskservice.projectbase.entity.Segment;
import com.ietunnel.riskservice.projectbase.entity.vo.IQuery;
import com.ietunnel.riskservice.projectbase.service.SegmentService;
import com.ietunnel.riskservice.tunnel.entity.Tunnel;
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
 * 标段 前端控制器
 * </p>
 *
 * @author zxg
 * @since 2021-12-22
 */
@RestController
@RequestMapping("/riskservice/segment")
@Api(description = "基础模块-标段管理")
@CrossOrigin
public class SegmentController {

    @Autowired
    private TunnelService tunnelService;
    @Autowired
    private SegmentService segmentService;

    @ApiOperation(value = "项目办添加标段")
    @PostMapping("addOfficeSubSegment")
    public R addOfficeSubSegment(@RequestBody Segment segment){
        boolean save = segmentService.save(segment);
        if(save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有标段列表")
    @GetMapping("findAll")
    public R findAllSegment() {
        //调用service的方法实现查询所有的操作
        List<Segment> list = segmentService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据id查询名下所有隧道列表")
    @GetMapping("findAllTunnel/{id}")
    public R findAllTunnelById(@PathVariable String id) {
        //调用service的方法实现查询所有的操作
        List<Tunnel> list = tunnelService.list(new QueryWrapper<Tunnel>().eq("tunnel_id",id));
        return R.ok().data("items",list);
    }

    @ApiOperation(value="根据id查询标段")
    @GetMapping("getSegment/{id}")
    public R getSegment(@PathVariable String id) {
        Segment segment = segmentService.getById(id);
        return R.ok().data("segment",segment);
    }

    @ApiOperation(value = "逻辑删除标段")
    @DeleteMapping("removeSegment/{id}")
    public R removeSegment(@ApiParam(name = "id", value = "标段ID", required = true)
                                 @PathVariable String id) {
        //
        boolean flag = segmentService.removeById(id);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value="修改标段信息")
    @PostMapping("updateSegment")
    //@RequestBody:jason格式
    public R updateSegment(@RequestBody Segment segment) {
        boolean flag = segmentService.updateById(segment);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //条件查询带分页的方法
    @PostMapping("pageSegmentCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询标段")
    public R pageSegmentCondition(@PathVariable long current, @PathVariable long limit,
                                        @RequestBody(required = false) IQuery segmentQuery) {
        //创建page对象
        Page<Segment> pageSegment = new Page<>(current,limit);

        //构建条件
        QueryWrapper<Segment> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String name = segmentQuery.getName();
        String begin = segmentQuery.getBegin();
        String end = segmentQuery.getEnd();
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
        segmentService.page(pageSegment,wrapper);

        long total = pageSegment.getTotal();//总记录数
        List<Segment> records = pageSegment.getRecords(); //数据list集合
        return R.ok().data("total",total).data("rows",records);
    }

    // 标段分类列表(树形)
    @GetMapping("getAllOfficeSegment")
    @ApiOperation(value = "项目办和标段的树形结构")
    public R getAllOfficeSegment(){
        List<OneSegment> list = segmentService.getAllOneTowSubject();
        return R.ok().data("list",list);
    }
}

