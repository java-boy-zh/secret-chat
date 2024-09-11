package com.itchat.service;

import com.itchat.utils.PagedGridResult;
import com.itchat.vo.FriendCircleVO;

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
}
