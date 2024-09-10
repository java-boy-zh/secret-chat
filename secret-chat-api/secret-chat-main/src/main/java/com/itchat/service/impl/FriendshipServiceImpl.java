package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itchat.bo.ContactsBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.enums.YesOrNo;
import com.itchat.mapper.FriendshipMapper;
import com.itchat.mapper.FriendshipMapperCustom;
import com.itchat.pojo.Friendship;
import com.itchat.service.FriendshipService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 朋友关系表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
@Service
public class FriendshipServiceImpl extends BaseInfoProperties implements FriendshipService {

    @Resource
    private FriendshipMapper friendshipMapper;
    @Resource
    private FriendshipMapperCustom friendshipMapperCustom;

    /**
     * 根据自己的userId和朋友的userId查询出Friendship
     *
     * @param myId
     * @param friendId
     * @return
     */
    @Override
    public Friendship getFriendship(String myId, String friendId) {
        LambdaQueryWrapper<Friendship> query = new LambdaQueryWrapper<>();
        query.eq(Friendship::getMyId, myId)
                .eq(Friendship::getFriendId, friendId);
        return friendshipMapper.selectOne(query);
    }

    /**
     * 查询我的好友列表(通讯录)
     *
     * @param myId
     * @param needBlack 查询黑名单
     * @return
     */
    @Override
    public List<ContactsBO> queryMyFriends(String myId, boolean needBlack) {
        Map<String, Object> map = new HashMap<>();
        map.put("myId", myId);
        map.put("needBlack", needBlack);

        return friendshipMapperCustom.queryMyFriends(map);
    }

    /**
     * 修改我的好友的备注名
     *
     * @param myId
     * @param friendId
     * @param friendRemark
     */
    @Override
    @Transactional
    public void updateFriendRemark(String myId,
                                   String friendId,
                                   String friendRemark) {
        LambdaQueryWrapper<Friendship> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(Friendship::getMyId, myId);
        updateWrapper.eq(Friendship::getFriendId, friendId);

        Friendship friendship = new Friendship();
        friendship.setFriendRemark(friendRemark);
        friendship.setUpdatedTime(LocalDateTime.now());

        friendshipMapper.update(friendship, updateWrapper);
    }

    /**
     * 拉黑或者恢复好友
     *
     * @param myId
     * @param friendId
     * @param yesOrNo
     */
    @Override
    @Transactional
    public void updateBlackList(String myId,
                                String friendId,
                                YesOrNo yesOrNo) {
        LambdaQueryWrapper<Friendship> updateWrapper = new LambdaQueryWrapper<>();
        updateWrapper.eq(Friendship::getMyId, myId);
        updateWrapper.eq(Friendship::getFriendId, friendId);

        Friendship friendship = new Friendship();
        friendship.setIsBlack(yesOrNo.type);
        friendship.setUpdatedTime(LocalDateTime.now());

        friendshipMapper.update(friendship, updateWrapper);
    }

    @Transactional
    @Override
    public void delete(String myId, String friendId) {

        LambdaQueryWrapper<Friendship> deleteWrapper1 = new LambdaQueryWrapper<>();
        deleteWrapper1.eq(Friendship::getMyId, myId);
        deleteWrapper1.eq(Friendship::getFriendId, friendId);

        friendshipMapper.delete(deleteWrapper1);

        LambdaQueryWrapper<Friendship> deleteWrapper2 = new LambdaQueryWrapper<>();
        deleteWrapper2.eq(Friendship::getMyId, friendId);
        deleteWrapper2.eq(Friendship::getFriendId, myId);

        friendshipMapper.delete(deleteWrapper2);
    }
}
