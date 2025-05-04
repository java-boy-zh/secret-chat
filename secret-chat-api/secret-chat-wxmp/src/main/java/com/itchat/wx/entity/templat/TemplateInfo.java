package com.itchat.wx.entity.templat;

import lombok.Data;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 模板信息实体类
 * @Version V1.0
 */
@Data
public class TemplateInfo {

    // 模板ID
    private String templateId;

    // 模板标题
    private String title;

    // 模板所属行业的一级行业
    private String primaryIndustry;

    // 模板所属行业的二级行业
    private String deputyIndustry;

    // 模板内容
    private String content;

    // 模板示例
    private String example;
}
