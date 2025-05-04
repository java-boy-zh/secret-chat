package com.itchat.wx.entity.templat;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 行业信息实体类
 * @Version V1.0
 */

@Data
public class IndustryInfo {
    @JSONField(name = "primary_industry")
    private Industry primaryIndustry;

    @JSONField(name = "secondary_industry")
    private Industry secondaryIndustry;

    @Data
    public static class Industry {
        @JSONField(name = "first_class")
        private String firstClass;

        @JSONField(name = "second_class")
        private String secondClass;
    }

}
