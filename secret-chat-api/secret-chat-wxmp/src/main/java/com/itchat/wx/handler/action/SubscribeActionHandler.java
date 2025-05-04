package com.itchat.wx.handler.action;

import com.itchat.wx.constant.MessageConstant;
import com.itchat.wx.enums.ActionOrMessageType;
import com.itchat.wx.utils.msg.ResultUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 关注公众号消息处理器
 * @Version V1.0
 */
@Component
public class SubscribeActionHandler implements ActionHandler {

    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.SUBSCRIBE;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {

        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(MessageConstant.TOUSERNAME, messageMap.get(MessageConstant.FROMUSERNAME));
        msgMap.put(MessageConstant.FROMUSERNAME, messageMap.get(MessageConstant.TOUSERNAME));

        msgMap.put(MessageConstant.CONTENT, "感谢关注本公众号！");

        return ResultUtil.buildTextResponse(msgMap);
    }
}
