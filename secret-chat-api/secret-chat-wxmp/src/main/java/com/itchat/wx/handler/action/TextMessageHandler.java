package com.itchat.wx.handler.action;

import com.alibaba.fastjson2.JSONObject;
import com.itchat.wx.config.WeChatConfig;
import com.itchat.wx.constant.MessageConstant;
import com.itchat.wx.entity.user.SubscribedUser;
import com.itchat.wx.enums.ActionOrMessageType;
import com.itchat.wx.utils.http.HttpUtil;
import com.itchat.wx.utils.msg.ResultUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2024年12月28日 13:42
 * @Description 文字消息处理器
 * @Version V1.0
 */
@Component
public class TextMessageHandler implements ActionHandler {
    @Resource
    private WeChatConfig weChatConfig;

    @Override
    public ActionOrMessageType getMessageType() {
        return ActionOrMessageType.TEXT;
    }

    @Override
    public String getMessage(Map<String, String> messageMap) {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put(MessageConstant.TOUSERNAME, messageMap.get(MessageConstant.FROMUSERNAME));
        msgMap.put(MessageConstant.FROMUSERNAME, messageMap.get(MessageConstant.TOUSERNAME));


        String content = messageMap.get(MessageConstant.CONTENT);

        String response = "";
        if ("图文".equals(content)) {
            response = ResultUtil.buildNewsResponse(msgMap);
        }

        return response;
    }

    /*
       引导报名活动用户访问地址进行授权，之后改用模版消息来封装
    */
    private String createSignUpRedirectUri() {
        //获得关注者的信息（微信号、昵称、头像等）并存入微信号昵称对应表
        //先引导用户访问地址进行授权，之后改用模版消息来封装
        String url = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s#wechat_redirect",
                weChatConfig.getAppId(),
                //redirect_uri用户同意后跳转的地址
                "http://" + weChatConfig.getQrCodeCallbackUrl() + "/getSignUpUserInfo",
                //scope
                "snsapi_userinfo");
        return "点击链接：<a href=\"" + url + "\">参与活动</a>";
    }

    public String getSignUpUserInfo(String code) {
        //根据code获取Access_token的地址
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                weChatConfig.getAppId(),
                weChatConfig.getAppSecret(),
                code);
        //发送get请求获得access_token
        String result = HttpUtil.doGet(url);
        String accessToken = JSONObject.parseObject(result).getString("access_token");
        String openId = JSONObject.parseObject(result).getString("access_token");
        //拿到access-token后拉取用户的基本信息 access_token openid
        String userInfoUrl = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN",
                accessToken,
                openId);
        //拉取用户信息
        String userInfo = HttpUtil.doGet(userInfoUrl);
        //解析获得微信号、昵称、头像
        SubscribedUser user = JSONObject.parseObject(userInfo, SubscribedUser.class);
        return user.getNickname();
    }

}
