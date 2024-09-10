package com.itchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itchat.bo.ContactsBO;
import com.itchat.pojo.Friendship;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 朋友关系表 拓展Mapper 接口
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
public interface FriendshipMapperCustom extends BaseMapper<Friendship> {

    List<ContactsBO> queryMyFriends(@Param("paramMap") Map<String, Object> map);
}
