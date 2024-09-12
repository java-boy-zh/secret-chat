package com.itchat.mapper;

import com.itchat.bo.CommentBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论表 拓展Mapper 接口
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
public interface CommentMapperCustom {

    /**
     * 根据朋友圈ID查询评论
     *
     * @param map
     * @return
     */
    List<CommentBO> queryFriendCircleComments(@Param("paramMap") Map<String, Object> map);
}
