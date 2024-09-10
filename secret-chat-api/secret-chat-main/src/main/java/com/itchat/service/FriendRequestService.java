package com.itchat.service;

import com.itchat.vo.NewFriendRequestVO;

/**
 * <p>
 * 好友请求记录表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
public interface FriendRequestService {

    /**
     * 新增新的好友请求
     * @param friendRequestVO
     */
    void addNewRequest(NewFriendRequestVO friendRequestVO);

}
