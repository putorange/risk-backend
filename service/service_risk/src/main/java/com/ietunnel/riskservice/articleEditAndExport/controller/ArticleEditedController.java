package com.ietunnel.riskservice.articleEditAndExport.controller;


import com.ietunnel.commonutils.R;
import com.ietunnel.riskservice.articleEditAndExport.entity.ArticleEdited;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.OneChapter;
import com.ietunnel.riskservice.articleEditAndExport.service.ArticleEditedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhy
 * @since 2022-05-15
 */
@CrossOrigin
@Api(description = "文章编辑操作")
@RestController
@RequestMapping("/riskservice/articleEdited/article-edited")
public class ArticleEditedController {
    @Autowired
    private ArticleEditedService articleEditedService;

    // 章节分类列表(树形显示)
    @ApiOperation(value = "获取所有的章节树形显示")
    @GetMapping("/getAllChapter")
    public R getAllChapter(){
        // list集合中的泛型是一级分类
        List<OneChapter> list = articleEditedService.getAllChapter();
        return R.ok().data("list",list);
    }

    // 根据章节id查询章节内容
    @ApiOperation(value = "根据章节id查询章节内容")
    @GetMapping("/findChapterDescription/{id}")
    public R getChapterDescription(@PathVariable String id){
        ArticleEdited articleEdited = articleEditedService.getById(id);
        return R.ok().data("article", articleEdited);
    }

    // 根据章节id修改章节内容
    @ApiOperation(value = "根据章节id修改章节内容")
    @PostMapping("/updateChapterDescription")
    public R updateChapterDescription(@RequestBody ArticleEdited articleEdited){
        boolean flag = articleEditedService.updateById(articleEdited);
        if (flag){
            return R.ok();
        } else {
            return R.error();
        }
    }
}

