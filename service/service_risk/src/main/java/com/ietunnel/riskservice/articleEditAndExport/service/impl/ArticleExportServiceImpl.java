package com.ietunnel.riskservice.articleEditAndExport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ietunnel.riskservice.articleEditAndExport.entity.ArticleEdited;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.OneChapter;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.ThreeChapter;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.TwoChapter;
import com.ietunnel.riskservice.articleEditAndExport.mapper.ArticleEditedMapper;
import com.ietunnel.riskservice.articleEditAndExport.service.ArticleExportService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleExportServiceImpl extends ServiceImpl<ArticleEditedMapper, ArticleEdited> implements ArticleExportService {


    @Override
    public String getAllContent() {

        // 1 查询所有的一级分类 parent_id = 0
        QueryWrapper<ArticleEdited> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<ArticleEdited> oneChapterList = baseMapper.selectList(wrapperOne);

        // 2 查询所有的二级分类 parent_id 等于一级分类中的 chapterId
        List<ArticleEdited> towChapterList = new ArrayList<>();
        // 查询所有一级分类中的chapterId与parent_id比较
        for (ArticleEdited articleEdited : oneChapterList) {
            QueryWrapper<ArticleEdited> wrapperTow = new QueryWrapper<>();
            wrapperTow.eq("parent_id", articleEdited.getChapterId());
            towChapterList.addAll(baseMapper.selectList(wrapperTow));
        }

        // 3 查询所有的三级分类 parent_id 等于二级分类中的 chapterId
        List<ArticleEdited> threeChapterList = new ArrayList<>();
        // 查询所有的二级分类中的chapterID与parent_id比较
        for (ArticleEdited articleEdited : towChapterList) {
            QueryWrapper<ArticleEdited> wrapperThree = new QueryWrapper<>();
            wrapperThree.eq("parent_id", articleEdited.getChapterId());
            threeChapterList.addAll(baseMapper.selectList(wrapperThree));
        }

        String content = "";
        // 遍历oneChapterList集合
        for (ArticleEdited oneArticleEdited : oneChapterList) {

            for (ArticleEdited twoArticleEdited : towChapterList) {
                if(twoArticleEdited.getParentId().equals(oneArticleEdited.getChapterId())){
                    // 取二级目录下的content值
                    if(twoArticleEdited.getChapterDescription() != null){
                        content += twoArticleEdited.getChapterDescription();
                    }
                    for (ArticleEdited threeArticleEdited : threeChapterList){
                        if(threeArticleEdited.getParentId().equals(twoArticleEdited.getChapterId())){
                            // 取三级目录下文章的content值
                            if(threeArticleEdited.getChapterDescription() != null){
                                content += threeArticleEdited.getChapterDescription();
                            }
                        }
                    }
                }
            }
        }
        return content;
    }

}


