package com.ietunnel.riskservice.articleEditAndExport.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: TwoChapter
 * @description: TODO 类描述
 * @author: huanyuzhang
 * @date: 2022/5/15
 **/
@Data
public class TwoChapter {
    private String chapterId;
    private String chapterTitle;
    // 一个二级分类有多个三级分类
    private List<ThreeChapter> children = new ArrayList<>();
}
