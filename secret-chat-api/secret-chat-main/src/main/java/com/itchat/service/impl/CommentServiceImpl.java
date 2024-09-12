package com.itchat.service.impl;

import com.itchat.bo.CommentBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.CommentMapper;
import com.itchat.mapper.CommentMapperCustom;
import com.itchat.pojo.Comment;
import com.itchat.pojo.Users;
import com.itchat.service.CommentService;
import com.itchat.service.UsersService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.vo.CommentVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
@Service
public class CommentServiceImpl extends BaseInfoProperties implements CommentService {

    @Resource
    private CommentMapper commentMapper;
    @Resource
    private CommentMapperCustom commentMapperCustom;
    @Resource
    private UsersService usersService;

    /**
     * 创建发表评论
     *
     * @param commentVO
     */
    @Override
    @Transactional
    public CommentBO createComment(CommentVO commentVO) {
        // 新增留言
        Comment pendingComment = CopyBeanUtils.copy(commentVO, Comment.class);
        pendingComment.setCreatedTime(LocalDateTime.now());

        commentMapper.insert(pendingComment);

        // 留言后的最新评论数据需要返回给前端（提供前端做的扩展数据）
        CommentBO commentBO = new CommentBO();
        BeanUtils.copyProperties(pendingComment, commentVO);

        Users commentUser = usersService.getUserById(commentBO.getCommentUserId());
        commentBO.setCommentUserNickname(commentUser.getNickname());
        commentBO.setCommentUserFace(commentUser.getFace());
        commentBO.setCommentId(pendingComment.getId());

        return commentBO;
    }

    /**
     * 根据朋友圈ID查询评论
     *
     * @param friendCircleId
     * @return
     */
    @Override
    public List<CommentBO> queryAll(String friendCircleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("friendCircleId", friendCircleId);

        return commentMapperCustom.queryFriendCircleComments(map);
    }
}
