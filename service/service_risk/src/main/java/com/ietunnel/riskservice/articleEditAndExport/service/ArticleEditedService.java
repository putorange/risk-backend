package com.ietunnel.riskservice.articleEditAndExport.service;

import com.ietunnel.riskservice.articleEditAndExport.entity.ArticleEdited;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ietunnel.riskservice.articleEditAndExport.entity.chapter.OneChapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhy
 * @since 2022-05-15
 */
public interface ArticleEditedService extends IService<ArticleEdited> {

    /**
     * 查询所有章节分类
     * @return
     */
    List<OneChapter> getAllChapter();



}
