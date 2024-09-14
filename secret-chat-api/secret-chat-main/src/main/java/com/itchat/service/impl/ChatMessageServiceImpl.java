package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.ChatMessageMapper;
import com.itchat.netty.ChatMsg;
import com.itchat.pojo.ChatMessage;
import com.itchat.service.ChatMessageService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.utils.PagedGridResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 分页查询消息内容
     *
     * @param senderId
     * @param receiverId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PagedGridResult queryChatMsgList(String senderId, String receiverId, Integer page, Integer pageSize) {
        Page<ChatMessage> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<ChatMessage>()
                .or(qw -> qw.eq(ChatMessage::getSenderId, senderId)
                        .eq(ChatMessage::getReceiverId, receiverId))
                .or(qw -> qw.eq(ChatMessage::getSenderId, receiverId)
                        .eq(ChatMessage::getReceiverId, senderId))
                .orderByDesc(ChatMessage::getChatTime);

        chatMessageMapper.selectPage(pageInfo, queryWrapper);

        // 获得列表后，倒着排序，因为聊天记录是展现最新的数据在聊天框的最下方，旧的数据在上方
        // 逆向逆序的处理
        List<ChatMessage> list = pageInfo.getRecords();
        List<ChatMessage> msgList  = list.stream().sorted(
                Comparator.comparing(ChatMessage::getChatTime)
        ).collect(Collectors.toList());

        pageInfo.setRecords(msgList);

        return setterPagedGridPlus(pageInfo);
    }

    /**
     * 签收消息
     *
     * @param msgId
     */
    @Override
    @Transactional
    public void updateMsgSignRead(String msgId) {
        ChatMessage message = new ChatMessage();
        message.setId(msgId);
        message.setIsRead(true);

        chatMessageMapper.updateById(message);
    }
}
