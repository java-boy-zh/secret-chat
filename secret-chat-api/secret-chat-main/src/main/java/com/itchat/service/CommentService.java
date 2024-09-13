package com.itchat.service;

import com.itchat.bo.CommentBO;
import com.itchat.vo.CommentVO;

import java.util.List;

/**
 * <p>
 * 评论表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
public interface CommentService {

    /**
     * 创建发表评论
     *
     * @param commentVO
     */
    CommentBO createComment(CommentVO commentVO);


    /**
     * 根据朋友圈ID查询评论
     * @param friendCircleId
     * @return
     */
    List<CommentBO> queryAll(String friendCircleId);

    /**
     * 删除评论
     * @param commentUserId
     * @param commentId
     * @param friendCircleId
     */
    void deleteComment(String commentUserId, String commentId, String friendCircleId);
}
