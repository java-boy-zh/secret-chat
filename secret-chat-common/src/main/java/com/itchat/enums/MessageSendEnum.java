package com.itchat.enums;

/**
 * @Desc: 消息发送类型
 */
public enum MessageSendEnum {
    SEND_TO_FRIEND(1, "发送给朋友"),
    SEND_TO_ME(2, "发送给自己");

    public final Integer type;
    public final String value;

    MessageSendEnum(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
