package com.itchat.service;

import com.itchat.bo.ContactsBO;
import com.itchat.pojo.Friendship;

import java.util.List;

/**
 * <p>
 * 朋友关系表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
public interface FriendshipService {

    /**
     * 根据自己的userId和朋友的userId查询出Friendship
     *
     * @param myId
     * @param friendId
     * @return
     */
    Friendship getFriendship(String myId, String friendId);

    /**
     * 查询我的好友列表(通讯录)
     *
     * @param myId
     * @param needBlack 查询黑名单
     * @return
     */
    List<ContactsBO> queryMyFriends(String myId, boolean needBlack);
}
