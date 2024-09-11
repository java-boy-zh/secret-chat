package com.itchat.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itchat.bo.FriendCircleBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.FriendCircleMapper;
import com.itchat.mapper.FriendCircleMapperCustom;
import com.itchat.pojo.FriendCircle;
import com.itchat.service.FriendCircleService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.FriendCircleVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
    @Resource
    private FriendCircleMapperCustom friendCircleMapperCustom;

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

    /**
     * 查询朋友圈信息
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PagedGridResult queryList(String userId,
                                     Integer page,
                                     Integer pageSize) {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        Page<FriendCircleBO> pageInfo = new Page<>(page, pageSize);

        friendCircleMapperCustom.queryFriendCircleList(pageInfo, map);

        return setterPagedGridPlus(pageInfo);
    }
}
