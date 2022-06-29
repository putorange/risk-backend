package com.ietunnel.riskservice.articleEditAndExport.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ietunnel.riskservice.articleEditAndExport.entity.ArticleEdited;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.OneChapter;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.ThreeChapter;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.TwoChapter;
import com.ietunnel.riskservice.articleEditAndExport.mapper.ArticleEditedMapper;
import com.ietunnel.riskservice.articleEditAndExport.service.ArticleEditedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhy and zxg
 * @since 2022-05-15
 */
@Service
public class ArticleEditedServiceImpl extends ServiceImpl<ArticleEditedMapper, ArticleEdited> implements ArticleEditedService {

    private   String content = "";
    @Override
    public List<OneChapter> getAllChapter() {
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

        // 创建list集合用于存储最终封装的数据
        List<OneChapter> finalArticleList = new ArrayList<>();

        // 4 封装一级分类
        // 查询出来所有的一级分类list集合遍历 得到每一个一级分类 获取每个一级分类对象值
        // 封装到要求的list集合里面 List<OneChapter> finalArticleList

        // 遍历oneChapterList集合
        for (ArticleEdited oneArticleEdited : oneChapterList) {
            // articleEdited是oneChapterList中的每一个对象
            // 把articleEdited值提取出来，放入oneChapter里面
            OneChapter oneChapter = new OneChapter();
            // 把articleEdited的值复制到oneChapter中，条件是双方的属性名一致
            BeanUtils.copyProperties(oneArticleEdited, oneChapter);
            // 多个oneChapter放到finalArticleList中
            finalArticleList.add(oneChapter);

            // 5 封装二级分类
            // 在一级分类的循环遍历查询所有的二级分类
            // 创建list集合封装每一个一级分类的二级分类
            List<TwoChapter> twoFinalChapterList = new ArrayList<>();
            // 遍历二级分类list集合
            for (ArticleEdited twoArticleEdited : towChapterList) {
                // twoArticleEdited代表每个二级分类
                // 判断二级分类的parentId和一级分类的chapterID之间的关系
                if (twoArticleEdited.getParentId().equals(oneArticleEdited.getChapterId())){
                    // 把twoChapter值复制到twoFinalChapterList里面
                    TwoChapter twoChapter = new TwoChapter();
                    BeanUtils.copyProperties(twoArticleEdited, twoChapter);
                    twoFinalChapterList.add(twoChapter);

                    // 取二级目录下的content值
                    if(twoArticleEdited.getChapterDescription() != null){
                        content += twoArticleEdited.getChapterDescription();
                    }

                    // 6 封装三级分类
                    // 在二级的循环遍历查询所有的三级分类
                    // 创建list集合封装每一个二级分类的三级分类
                    List<ThreeChapter> threeFinalChapterList = new ArrayList<>();
                    for (ArticleEdited threeArticleEdited : threeChapterList){
                        // threeArticleEdited代表每一个三级分类
                        // 判断三级分类的parentId和一级分类的chapterId之间的关系
                        if (threeArticleEdited.getParentId().equals(twoArticleEdited.getChapterId())){
                            // 把threeChapter值复制到threeFinalChapterList里面
                            ThreeChapter threeChapter = new ThreeChapter();
                            BeanUtils.copyProperties(threeArticleEdited, threeChapter);
                            threeFinalChapterList.add(threeChapter);
                        }

                        // 取三级目录下文章的content值
                        if(threeArticleEdited.getChapterDescription() != null){
                            content += threeArticleEdited.getChapterDescription();
                        }
                    }
                    twoChapter.setChildren(threeFinalChapterList);
                }
            }
            oneChapter.setChildren(twoFinalChapterList);
        }

        return finalArticleList;
    }



}
