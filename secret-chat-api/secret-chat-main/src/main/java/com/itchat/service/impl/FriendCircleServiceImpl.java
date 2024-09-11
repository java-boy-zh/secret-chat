package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itchat.bo.FriendCircleBO;
import com.itchat.common.BaseInfoProperties;
import com.itchat.mapper.FriendCircleLikedMapper;
import com.itchat.mapper.FriendCircleMapper;
import com.itchat.mapper.FriendCircleMapperCustom;
import com.itchat.pojo.FriendCircle;
import com.itchat.pojo.FriendCircleLiked;
import com.itchat.pojo.Users;
import com.itchat.service.FriendCircleService;
import com.itchat.service.UsersService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.utils.PagedGridResult;
import com.itchat.vo.FriendCircleVO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
    @Resource
    private UsersService usersService;
    @Resource
    private FriendCircleLikedMapper friendCircleLikedMapper;

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

    /**
     * 点赞朋友圈
     *
     * @param friendCircleId
     * @param userId
     */
    @Override
    @Transactional
    public void like(String friendCircleId, String userId) {
        // 根据朋友圈的主键ID查询归属人(发布人)
        FriendCircle friendCircle = this.selectFriendCircle(friendCircleId);

        // 根据用户主键ID查询点赞人
        Users users = usersService.getUserById(userId);

        FriendCircleLiked friendCircleLiked = new FriendCircleLiked();
        friendCircleLiked.setFriendCircleId(friendCircleId);
        friendCircleLiked.setBelongUserId(friendCircle.getUserId());
        friendCircleLiked.setLikedUserId(userId);
        friendCircleLiked.setLikedUserName(users.getNickname());
        friendCircleLiked.setCreatedTime(LocalDateTime.now());

        friendCircleLikedMapper.insert(friendCircleLiked);

        // 点赞过后，朋友圈的对应点赞数累加1
        redis.increment(REDIS_FRIEND_CIRCLE_LIKED_COUNTS + ":" + friendCircleId, 1);
        // 标记哪个用户点赞过该朋友圈
        redis.setnx(REDIS_DOES_USER_LIKE_FRIEND_CIRCLE + ":" + friendCircleId + ":" + userId, userId);
    }

    /**
     * 取消(删除)点赞朋友圈
     *
     * @param friendCircleId
     * @param userId
     */
    @Override
    @Transactional
    public void unlike(String friendCircleId, String userId) {
        // 从数据库中删除点赞关系
        LambdaQueryWrapper deleteWrapper = new LambdaQueryWrapper<FriendCircleLiked>()
                .eq(FriendCircleLiked::getFriendCircleId, friendCircleId)
                .eq(FriendCircleLiked::getLikedUserId, userId);
        friendCircleLikedMapper.delete(deleteWrapper);

        // 取消点赞过后，朋友圈的对应点赞数累减1
        redis.decrement(REDIS_FRIEND_CIRCLE_LIKED_COUNTS + ":" + friendCircleId, 1);

        // 删除标记的那个用户点赞过的朋友圈
        redis.del(REDIS_DOES_USER_LIKE_FRIEND_CIRCLE + ":" + friendCircleId + ":" + userId);
    }

    /**
     * 查询朋友圈的点赞列表
     *
     * @param friendCircleId
     * @return
     */
    @Override
    public List<FriendCircleLiked> queryLikedFriends(String friendCircleId) {
        LambdaQueryWrapper queryWrapper = new LambdaQueryWrapper<FriendCircleLiked>()
                .eq(FriendCircleLiked::getFriendCircleId, friendCircleId);
        return friendCircleLikedMapper.selectList(queryWrapper);
    }

    /**
     * 判断当前用户是否点赞过朋友圈
     *
     * @param friendCircleId
     * @param userId
     * @return
     */
    @Override
    public boolean doILike(String friendCircleId, String userId) {
        String isExist = redis.get(REDIS_DOES_USER_LIKE_FRIEND_CIRCLE + ":" + friendCircleId + ":" + userId);
        return StringUtils.isNotBlank(isExist);
    }

    private FriendCircle selectFriendCircle(String friendCircleId) {
        return friendCircleMapper.selectById(friendCircleId);
    }

    /**
     * 删除朋友圈
     *
     * @param friendCircleId
     * @param userId
     */
    @Override
    public void delete(String friendCircleId, String userId) {
        LambdaQueryWrapper<FriendCircle> deleteWrapper = new LambdaQueryWrapper<>();
        deleteWrapper.eq(FriendCircle::getId, friendCircleId);
        deleteWrapper.eq(FriendCircle::getUserId, userId);

        friendCircleMapper.delete(deleteWrapper);
    }
}
