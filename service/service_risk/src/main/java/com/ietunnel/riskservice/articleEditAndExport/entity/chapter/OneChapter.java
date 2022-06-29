package com.ietunnel.riskservice.articleEditAndExport.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: OneChapter
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2022/5/15
 **/
@Data
public class OneChapter {
    private String chapterId;
    private String chapterTitle;
    // 一个一级分类有多个二级分类
    private List<TwoChapter> children = new ArrayList<>();
}
