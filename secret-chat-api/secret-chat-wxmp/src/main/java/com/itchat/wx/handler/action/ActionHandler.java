package com.itchat.wx.handler.action;


import com.itchat.wx.enums.ActionOrMessageType;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 16:25
 * @Description 消息处理器
 * @Version V1.0
 */
public interface ActionHandler {

    // 消息类型
    ActionOrMessageType getMessageType();

    // 获取回复消息
    String getMessage(Map<String,String> messageMap);
}
