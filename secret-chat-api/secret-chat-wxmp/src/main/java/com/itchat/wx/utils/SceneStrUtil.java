package com.itchat.wx.utils;

import com.itchat.wx.constant.WeChatQrcode;
import org.apache.commons.lang3.StringUtils;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2025年01月08日 21:31
 * @Description
 * @Version V1.0
 */
public class SceneStrUtil {
    public static String getSceneStr(String sceneStr) {
        if (StringUtils.isNotBlank(sceneStr) && sceneStr.startsWith(WeChatQrcode.QR_SCENE_STR)){
            sceneStr = sceneStr.replaceFirst(WeChatQrcode.QR_SCENE_STR, "");
        }
        return sceneStr;
    }
}
