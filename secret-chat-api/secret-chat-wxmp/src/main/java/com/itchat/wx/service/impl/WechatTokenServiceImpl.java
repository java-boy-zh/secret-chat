package com.itchat.wx.service.impl;

import com.itchat.utils.RedisOperator;
import com.itchat.wx.config.WeChatConfig;
import com.itchat.wx.constant.WeChatUrl;
import com.itchat.wx.entity.AccessToken;
import com.itchat.wx.service.WechatTokenService;
import com.itchat.wx.utils.JsonUtils;
import com.itchat.wx.utils.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信token服务实现类
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WechatTokenServiceImpl implements WechatTokenService {

    private final WeChatConfig weChatConfig;
    private final RedisOperator redisOperator;

    /**
     * access_token在redis中的key前缀
     */
    private static final String ACCESS_TOKEN_KEY = "wechat:access_token:";

    /**
     * access_token的过期时间，提前5分钟过期
     */
    private static final int EXPIRE_TIME = 7000;

    @Override
    public String getAccessToken() {
        // 先从redis中获取
        String accessToken = redisOperator.get(ACCESS_TOKEN_KEY);
        if (accessToken != null) {
            return accessToken;
        }

        // redis中没有，则重新获取
        return refreshAccessToken();
    }

    @Override
    public String refreshAccessToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");
        params.put("appid", weChatConfig.getAppId());
        params.put("secret", weChatConfig.getAppSecret());
        params.put("force_refresh", true);

        String url = WeChatUrl.ACCESS_TOKEN_URL;
        String result = HttpUtil.doPost(url,  JsonUtils.objectToJson(params), MediaType.APPLICATION_JSON);

        AccessToken accessToken = JsonUtils.jsonToPojo(result, AccessToken.class);
        if (accessToken == null || accessToken.getErrcode() != null) {
            log.error("获取access_token失败: {}", result);
        }

        // 将access_token存入redis
        redisOperator.set(ACCESS_TOKEN_KEY, accessToken.getAccessToken(), EXPIRE_TIME);

        return accessToken.getAccessToken();
    }

    /**
     * 构建查询字符串
     */
    private String buildQueryString(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return sb.toString();
    }
}
