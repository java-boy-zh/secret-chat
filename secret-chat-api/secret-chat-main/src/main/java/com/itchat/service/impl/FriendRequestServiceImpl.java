package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itchat.bo.NewFriendsBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.enums.FriendRequestVerifyStatus;
import com.itchat.enums.YesOrNo;
import com.itchat.mapper.FriendRequestMapper;
import com.itchat.mapper.FriendRequestMapperCustom;
import com.itchat.mapper.FriendshipMapper;
import com.itchat.pojo.FriendRequest;
import com.itchat.pojo.Friendship;
import com.itchat.service.FriendRequestService;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.NewFriendRequestVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 好友请求记录表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
@Service
public class FriendRequestServiceImpl extends BaseInfoProperties implements FriendRequestService {

    @Resource
    private FriendRequestMapper friendRequestMapper;
    @Resource
    private FriendshipMapper friendshipMapper;
    @Resource
    private FriendRequestMapperCustom friendRequestMapperCustom;

    /**
     * 新增新的好友请求
     *
     * @param friendRequestVO
     */
    @Override
    @Transactional
    public void addNewRequest(NewFriendRequestVO friendRequestVO) {
        // 先查询出以前的记录
        QueryWrapper queryWrapper = new QueryWrapper<FriendRequest>()
                .eq("my_id", friendRequestVO.getMyId())
                .eq("friend_id", friendRequestVO.getFriendId());
        FriendRequest friendRequest = friendRequestMapper.selectOne(queryWrapper);
        // 删除以前的记录
        friendRequestMapper.delete(queryWrapper);

        StringBuilder sb = new StringBuilder();
        if (friendRequest != null) sb.append(friendRequest.getVerifyMessage());

        // 再新增记录
        FriendRequest insertFriendRequest = new FriendRequest();
        String verifyMessageNew = friendRequestVO.getVerifyMessage();
        BeanUtils.copyProperties(friendRequestVO, insertFriendRequest);

        insertFriendRequest.setVerifyStatus(FriendRequestVerifyStatus.WAIT.type);
        insertFriendRequest.setRequestTime(LocalDateTime.now());
        if (!sb.isEmpty()) sb.append(",");
        sb.append(verifyMessageNew);
        insertFriendRequest.setVerifyMessage(sb.toString());

        friendRequestMapper.insert(insertFriendRequest);
    }

    /**
     * 分页查询 当前用户的好友请求列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PagedGridResult queryNewFriendList(String userId,
                                              Integer page,
                                              Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("mySelfId", userId);

        Page<NewFriendsBO> pageInfo = new Page<>(page, pageSize);
        friendRequestMapperCustom.queryNewFriendList(pageInfo, map);

        return setterPagedGridPlus(pageInfo);
    }

    /**
     * 通过好友请求
     *
     * @param friendRequestId
     * @param friendRemark
     */
    @Transactional
    @Override
    public void passNewFriend(String friendRequestId, String friendRemark) {

        FriendRequest friendRequest = getSingle(friendRequestId);
        String mySelfId = friendRequest.getFriendId();  // 通过方的用户id
        String myFriendId = friendRequest.getMyId();    // 被通过方的用户id

        // 创建双方的好友关系
        LocalDateTime nowTime = LocalDateTime.now();
        Friendship friendshipSelf = new Friendship();
        friendshipSelf.setMyId(mySelfId);
        friendshipSelf.setFriendId(myFriendId);
        friendshipSelf.setFriendRemark(friendRemark);
        friendshipSelf.setIsBlack(YesOrNo.NO.type);
        friendshipSelf.setIsMsgIgnore(YesOrNo.NO.type);
        friendshipSelf.setCreatedTime(nowTime);
        friendshipSelf.setUpdatedTime(nowTime);

        Friendship friendshipOpposite = new Friendship();
        friendshipOpposite.setMyId(myFriendId);
        friendshipOpposite.setFriendId(mySelfId);
        friendshipOpposite.setFriendRemark(friendRequest.getFriendRemark());
        friendshipOpposite.setIsBlack(YesOrNo.NO.type);
        friendshipOpposite.setIsMsgIgnore(YesOrNo.NO.type);
        friendshipOpposite.setCreatedTime(nowTime);
        friendshipOpposite.setUpdatedTime(nowTime);

        friendshipMapper.insert(friendshipSelf);
        friendshipMapper.insert(friendshipOpposite);

        // A通过B的请求之后，需要把双方的好友请求记录都设置为“通过”
        friendRequest.setVerifyStatus(FriendRequestVerifyStatus.SUCCESS.type);
        friendRequestMapper.updateById(friendRequest);

        // 还有一种情况，A添加B，B没有通过，所以A发出的好友请求过期了；
        // 但是，过期后，B向A发起好友请求，所以B被A通过后，那么两边的请求都应该“通过”
        LambdaQueryWrapper updateWrapper = new LambdaQueryWrapper<FriendRequest>()
                .eq(FriendRequest::getMyId, myFriendId)
                .eq(FriendRequest::getFriendId, mySelfId);
        FriendRequest requestOpposite = new FriendRequest();
        requestOpposite.setVerifyStatus(FriendRequestVerifyStatus.SUCCESS.type);
        friendRequestMapper.update(requestOpposite, updateWrapper);
    }

    private FriendRequest getSingle(String friendRequestId) {
        return friendRequestMapper.selectById(friendRequestId);
    }
}
