package com.itchat.service.impl;

import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.ChatMessageMapper;
import com.itchat.netty.ChatMsg;
import com.itchat.pojo.ChatMessage;
import com.itchat.service.ChatMessageService;
import com.itchat.utils.CopyBeanUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 聊天信息存储表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-14
 */
@Service
public class ChatMessageServiceImpl extends BaseInfoProperties implements ChatMessageService {
    @Resource
    private ChatMessageMapper chatMessageMapper;

    /**
     * 存储消息
     *
     * @param chatMsg
     */
    @Override
    @Transactional
    public void saveMessage(ChatMsg chatMsg) {
        ChatMessage chatMessage = CopyBeanUtils.copy(chatMsg, ChatMessage.class);
        // 手动设置聊天消息的主键ID
        chatMessage.setId(chatMsg.getMsgId());

        chatMessageMapper.insert(chatMessage);

        String receiverId = chatMsg.getReceiverId();
        String senderId = chatMsg.getSenderId();

        // 通过redis累加信息接收者的对应记录
        redis.incrementHash(CHAT_MSG_LIST + ":" + receiverId, senderId, 1);
    }
}
