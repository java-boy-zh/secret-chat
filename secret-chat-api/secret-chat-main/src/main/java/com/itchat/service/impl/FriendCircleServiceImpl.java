package com.itchat.service.impl;

import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.FriendCircleMapper;
import com.itchat.pojo.FriendCircle;
import com.itchat.service.FriendCircleService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.vo.FriendCircleVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 朋友圈表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-11
 */
@Service
public class FriendCircleServiceImpl extends BaseInfoProperties implements FriendCircleService {

    @Resource
    private FriendCircleMapper friendCircleMapper;

    /**
     * 发布朋友圈
     *
     * @param friendCircleVO
     */
    @Override
    public void publish(FriendCircleVO friendCircleVO) {
        FriendCircle inserFriendCircle =
                CopyBeanUtils.copy(friendCircleVO, FriendCircle.class);

        friendCircleMapper.insert(inserFriendCircle);
    }
}
