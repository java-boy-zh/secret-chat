package com.itchat.wx.handler.action;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson2.JSON;
import com.itchat.utils.RedisOperator;
import com.itchat.wx.config.WeChatConfig;
import com.itchat.wx.constant.MessageConstant;
import com.itchat.wx.constant.SseCacheThreadLocal;
import com.itchat.wx.constant.WeChatQrcode;
import com.itchat.wx.entity.WechatQrCode;
import com.itchat.wx.enums.ActionOrMessageType;
import com.itchat.wx.enums.StatusEnum;
import com.itchat.wx.utils.JsonUtils;
import com.itchat.wx.utils.SceneStrUtil;
import com.itchat.wx.utils.msg.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 微信登录公众号消息处理器
 * @Version V1.0
 */
@Component
public class WeChatLoginActionHandler implements ActionHandler {

    @Resource
    private WeChatConfig weChatConfig;
    @Resource
    private RedisOperator redisOperator;

    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.WE_CHAT_LOGIN;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {

        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(MessageConstant.TOUSERNAME, messageMap.get(MessageConstant.FROMUSERNAME));
        msgMap.put(MessageConstant.FROMUSERNAME, messageMap.get(MessageConstant.TOUSERNAME));

        // 需要判断是否第一次关注就需要直接登陆的用户
        String sceneStr = messageMap.get("EventKey") == null ? "" : messageMap.get("EventKey");
        sceneStr = SceneStrUtil.getSceneStr(sceneStr);
        String ticket = messageMap.get("Ticket") == null ? "" : messageMap.get("Ticket");
        String redisKey = WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr;

        if (redisOperator.keyIsExist(redisKey)) {
            Object obj = redisOperator.get(redisKey);
            WechatQrCode qrCode = JSON.parseObject(JSON.toJSONString(obj), WechatQrCode.class);
            if (qrCode != null && qrCode.getTicket().equals(ticket)) {
                // 设置扫描状态
                qrCode.setStatus(StatusEnum.SCANNED.getStatus());
                // openId
                qrCode.setOpenId(messageMap.get(MessageConstant.FROMUSERNAME));
                redisOperator.set(redisKey, JsonUtils.objectToJson(qrCode));
                redisOperator.expire(redisKey, WeChatQrcode.QRCODE_EXPIRE_SECONDS);

                String url = weChatConfig.getCallbackUrl() + "/auth/wechat/phoneLogin/" + sceneStr;
                String content = "点击链接：<a href=\"" + url + "\">登录</a>";
                msgMap.put(MessageConstant.CONTENT, content);

                // 推送sse
                SseEmitter emitter = SseCacheThreadLocal.get(sceneStr);
                if (emitter != null) {
                    try {
                        Map<Object, Object> res = MapUtil.builder()
                            .put("id", sceneStr)
                            .put("message", StatusEnum.SCANNED.getStatus())
                            .build();

                        emitter.send(res);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return ResultUtil.buildTextResponse(msgMap);
    }
}
