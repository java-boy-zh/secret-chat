package com.itchat.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itchat.bo.FriendCircleBO;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 朋友圈表 拓展Mapper 接口
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
public interface FriendCircleMapperCustom {

    Page<FriendCircleBO> queryFriendCircleList(@Param("page") Page<FriendCircleBO> pageInfo,
                                               @Param("paramMap") Map<String, Object> map);
}
