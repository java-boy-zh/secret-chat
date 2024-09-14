package com.itchat.service;

import com.itchat.bo.ContactsBO;
import com.itchat.enums.YesOrNo;
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

    /**
     * 修改我的好友的备注名
     * @param myId
     * @param friendId
     * @param friendRemark
     */
    void updateFriendRemark(String myId,
                                   String friendId,
                                   String friendRemark);

    /**
     * 拉黑或者恢复好友
     * @param myId
     * @param friendId
     * @param yesOrNo
     */
    void updateBlackList(String myId,
                                String friendId,
                                YesOrNo yesOrNo);

    /**
     * 删除好友关系
     * @param myId
     * @param friendId
     */
    void delete(String myId, String friendId);

    /**
     * 判断两个朋友之前的关系是否拉黑
     *
     * @param friendId1st
     * @param friendId2nd
     * @return
     */
    boolean isBlackEachOther(String friendId1st, String friendId2nd);
}
