package com.itchat.wx.entity.templat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024-01-18
 * @Description 微信模板消息实体类
 * @Version V1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxTemplateMessage {

    // 接收者openid
    private String touser;

    // 模板ID
    private String template_id;

    // 模板跳转链接
    private String url;

    // 跳小程序所需数据
    private MiniProgram miniprogram;

    // 防重入id
    private String client_msg_id;

    // 模板数据
    private Map<String, TemplateData> data;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MiniProgram {
        // 所需跳转到的小程序appid
        private String appid;

        // 所需跳转到小程序的具体页面路径
        private String pagepath;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TemplateData {
        private String value;
    }
}
