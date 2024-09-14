package com.itchat.service;

import com.itchat.netty.ChatMsg;
import com.itchat.utils.PagedGridResult;

/**
 * <p>
 * 聊天信息存储表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-14
 */
public interface ChatMessageService {

    /**
     * 存储消息
     * @param chatMsg
     */
    void saveMessage(ChatMsg chatMsg);

    /**
     * 分页查询消息内容
     * @param senderId
     * @param receiverId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryChatMsgList(String senderId, String receiverId, Integer page, Integer pageSize);

    /**
     * 签收消息
     * @param msgId
     */
    void updateMsgSignRead(String msgId);

}
