package com.itchat.wx.handler.action;

import com.itchat.wx.enums.ActionOrMessageType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 取消关注公众号消息处理器
 * @Version V1.0
 */
@Component
public class UnsubscribeActionHandler implements ActionHandler {
    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.UNSUBSCRIBE;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {
        // 取消关注是不需要回复消息的 只需要操作用户即可
        System.out.println("取消关注是不需要回复消息的 只需要操作用户即可");
        return null;
    }
}
