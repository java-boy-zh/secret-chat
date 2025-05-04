package com.itchat.wx.service.impl;

import cn.hutool.core.map.MapUtil;
import com.google.protobuf.ServiceException;
import com.itchat.utils.RedisOperator;
import com.itchat.wx.constant.SseCacheThreadLocal;
import com.itchat.wx.constant.WeChatQrcode;
import com.itchat.wx.constant.WeChatUrl;
import com.itchat.wx.entity.QrCodeTicket;
import com.itchat.wx.entity.WechatQrCode;
import com.itchat.wx.enums.StatusEnum;
import com.itchat.wx.service.WechatAuthService;
import com.itchat.wx.service.WechatTokenService;
import com.itchat.wx.utils.JsonUtils;
import com.itchat.wx.utils.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class WechatAuthServiceImpl implements WechatAuthService {

    private final WechatTokenService wechatTokenService;
    private final RedisOperator redisOperator;

    @Override
    public WechatQrCode generateQrCode() {
        return generateTemporaryQrCode();
    }

    /**
     * 生成临时二维码
     * @return WechatQrCode对象
     */
    public WechatQrCode generateTemporaryQrCode() {
        try {
            String sceneStr = UUID.randomUUID().toString();
            String accessToken = wechatTokenService.getAccessToken();

            // 构建请求参数
            Map<String, Object> scene = new HashMap<>();
            scene.put("scene_str", sceneStr);

            Map<String, Object> actionInfo = new HashMap<>();
            actionInfo.put("scene", scene);

            Map<String, Object> request = new HashMap<>();
            request.put("expire_seconds", WeChatQrcode.QRCODE_EXPIRE_SECONDS);
            request.put("action_name", "QR_SCENE");
            request.put("action_info", actionInfo);

            // 调用微信接口创建二维码ticket
            String url = WeChatUrl.QRCODE_CREATE_URL + "?access_token=" + accessToken;
            String result = HttpUtil.doPost(url, JsonUtils.objectToJson(request), MediaType.APPLICATION_JSON);

            QrCodeTicket ticket = JsonUtils.jsonToPojo(result, QrCodeTicket.class);
            if (ticket == null || ticket.getErrcode() != null) {
                log.error("创建二维码ticket失败: {}", result);
                throw new ServiceException("创建二维码ticket失败: " + (ticket != null ? ticket.getErrmsg() : "未知错误"));
            }

            WechatQrCode qrCode = new WechatQrCode();
            qrCode.setSceneStr(sceneStr);
            qrCode.setTicket(ticket.getTicket());
            qrCode.setQrCodeUrl(WeChatUrl.SHOW_QRCODE_URL + "?ticket=" + ticket.getTicket());
            qrCode.setStatus(StatusEnum.WAITING.getStatus());
            qrCode.setCreateTime(new Date());

            // 设置二维码过期时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.SECOND, WeChatQrcode.QRCODE_EXPIRE_SECONDS);
            qrCode.setExpireTime(calendar.getTime());

            // 将二维码信息存入Redis
            redisOperator.set(WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr, JsonUtils.objectToJson(qrCode));
            redisOperator.expire(WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr, WeChatQrcode.QRCODE_EXPIRE_SECONDS);

            return qrCode;
        } catch (Exception e) {
            log.error("生成微信临时二维码失败", e);
            throw new RuntimeException("生成微信临时二维码失败", e);
        }
    }

    /**
     * 生成永久二维码
     * @param sceneStr 场景值字符串
     * @return WechatQrCode对象
     */
    public WechatQrCode generatePermanentQrCode(String sceneStr) {
        try {
            String accessToken = wechatTokenService.getAccessToken();

            // 构建请求参数
            Map<String, Object> scene = new HashMap<>();
            scene.put("scene_str", sceneStr);

            Map<String, Object> actionInfo = new HashMap<>();
            actionInfo.put("scene", scene);

            Map<String, Object> request = new HashMap<>();
            request.put("action_name", "QR_LIMIT_STR_SCENE");
            request.put("action_info", actionInfo);

            // 调用微信接口创建二维码ticket
            String url = WeChatUrl.QRCODE_CREATE_URL + "?access_token=" + accessToken;
            String result = HttpUtil.doPost(url, JsonUtils.objectToJson(request), MediaType.APPLICATION_JSON);

            QrCodeTicket ticket = JsonUtils.jsonToPojo(result, QrCodeTicket.class);
            if (ticket == null || ticket.getErrcode() != null) {
                log.error("创建二维码ticket失败: {}", result);
                throw new ServiceException("创建二维码ticket失败: " + (ticket != null ? ticket.getErrmsg() : "未知错误"));
            }

            WechatQrCode qrCode = new WechatQrCode();
            qrCode.setSceneStr(sceneStr);
            qrCode.setTicket(ticket.getTicket());
            qrCode.setQrCodeUrl(WeChatUrl.SHOW_QRCODE_URL + "?ticket=" + ticket.getTicket());
            qrCode.setStatus(StatusEnum.WAITING.getStatus());
            qrCode.setCreateTime(new Date());

            // 永久二维码不需要设置过期时间
            qrCode.setExpireTime(null);

            // 将二维码信息存入Redis
            redisOperator.set(WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr, JsonUtils.objectToJson(qrCode));

            return qrCode;
        } catch (Exception e) {
            log.error("生成微信永久二维码失败", e);
            throw new RuntimeException("生成微信永久二维码失败", e);
        }
    }

    @Override
    public WechatQrCode checkScanStatus(String sceneStr) {
        Object obj = redisOperator.get(WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr);
        log.info("checkScanStatus -> {}", sceneStr);
        if (obj == null) {
            throw new RuntimeException("二维码不存在或已过期");
        }

        WechatQrCode qrCode = JsonUtils.jsonToPojo(JsonUtils.objectToJson(obj), WechatQrCode.class);
        if (qrCode == null || new Date().after(qrCode.getExpireTime())) {
            throw new RuntimeException("二维码已过期");
        }
        return qrCode;
    }

    @Override
    @Transactional
    public void login(String sceneStr) {
        String redisKey = WeChatQrcode.WECHAT_QRCODE_PREFIX + WeChatQrcode.QR_SCENE_STR + sceneStr;
        if (redisOperator.keyIsExist(redisKey)) {
            Object obj = redisOperator.get(redisKey);
            WechatQrCode qrCode = JsonUtils.jsonToPojo(JsonUtils.objectToJson(obj), WechatQrCode.class);
            // 设置扫描状态
            qrCode.setStatus(StatusEnum.CONFIRMED.getStatus());
            redisOperator.set(redisKey, JsonUtils.objectToJson(qrCode));
            redisOperator.expire(redisKey, WeChatQrcode.QRCODE_EXPIRE_SECONDS);

            // 推送sse
            SseEmitter emitter = SseCacheThreadLocal.get(sceneStr);
            if (emitter != null) {
                try {
                    Map<Object, Object> res = MapUtil.builder()
                            .put("id", sceneStr)
                            .put("openId", qrCode.getOpenId())
                            .put("message", StatusEnum.CONFIRMED.getStatus())
                            .build();

                    emitter.send(res);
                } catch (IOException e) {
                    log.error("推送sse失败", e);
                }
            }
        } else {
            log.error("二维码已过期");
        }
    }
}
