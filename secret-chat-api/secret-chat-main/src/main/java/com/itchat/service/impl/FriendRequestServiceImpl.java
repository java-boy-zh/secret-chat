package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itchat.common.BaseInfoProperties;
import com.itchat.enums.FriendRequestVerifyStatus;
import com.itchat.mapper.FriendRequestMapper;
import com.itchat.pojo.FriendRequest;
import com.itchat.service.FriendRequestService;
import com.itchat.vo.NewFriendRequestVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
}
