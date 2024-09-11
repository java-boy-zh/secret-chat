package com.itchat.service;

import com.itchat.pojo.FriendCircleLiked;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.FriendCircleVO;

import java.util.List;

/**
 * <p>
 * 朋友圈表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
public interface FriendCircleService {

    /**
     * 发布朋友圈
     * @param friendCircleVO
     */
    void publish(FriendCircleVO friendCircleVO);

    /**
     * 查询朋友圈信息
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult queryList(String userId, Integer page, Integer pageSize);

    /**
     * 点赞朋友圈
     * @param friendCircleId
     * @param userId
     */
    void like(String friendCircleId, String userId);

    /**
     * 取消(删除)点赞朋友圈
     * @param friendCircleId
     * @param userId
     */
    void unlike(String friendCircleId, String userId);

    /**
     * 查询朋友圈的点赞列表
     * @param friendCircleId
     * @return
     */
    List<FriendCircleLiked> queryLikedFriends(String friendCircleId);

    /**
     * 判断当前用户是否点赞过朋友圈
     * @param friendCircleId
     * @param userId
     * @return
     */
    boolean doILike(String friendCircleId, String userId);

    /**
     * 删除朋友圈
     * @param friendCircleId
     * @param userId
     */
    void delete(String friendCircleId, String userId);
}
