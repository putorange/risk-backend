package com.ietunnel.riskservice.articleEditAndExport.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhy
 * @since 2022-05-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ArticleEdited对象", description="")
public class ArticleEdited implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "章节id")
    @TableId(value = "chapter_id", type = IdType.ID_WORKER_STR)
    private String chapterId;

    @ApiModelProperty(value = "当前章节的父id")
    private String parentId;

    @ApiModelProperty(value = "章节名称")
    private String chapterTitle;

    @ApiModelProperty(value = "章节内容")
    private String chapterDescription;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private String isDelete;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
