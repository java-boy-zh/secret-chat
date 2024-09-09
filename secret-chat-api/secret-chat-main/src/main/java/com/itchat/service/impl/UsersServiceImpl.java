package com.itchat.service.impl;

import com.itchat.common.BaseInfoProperties;
import com.itchat.exceptions.GraceException;
import com.itchat.feigns.FileServiceFeign;
import com.itchat.mapper.UsersMapper;
import com.itchat.pojo.Users;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.service.UsersService;
import com.itchat.utils.CopyBeanUtils;
import com.itchat.vo.ModifyUserVO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
@Service
public class UsersServiceImpl extends BaseInfoProperties implements UsersService {

    @Resource
    private UsersMapper usersMapper;
    @Resource
    private FileServiceFeign fileServiceFeign;

    /**
     * 修改用户信息
     *
     * @param modifyUserVO 前端传递的用户信息
     */
    @Override
    @Transactional
    public void modifyUserInfo(ModifyUserVO modifyUserVO) {
        // 密聊号一年只能修改一次
        String userId = modifyUserVO.getUserId();
        String wechatNum = modifyUserVO.getWechatNum();

        Users updateUser = CopyBeanUtils.copy(modifyUserVO, Users.class);

        if (StringUtils.isBlank(userId)) {
            GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_ERROR);
        }

        if (StringUtils.isNotBlank(wechatNum)) {
            String isExist = redis.get(REDIS_USER_ALREADY_UPDATE_WECHAT_NUM + ":" + userId);
            if (StringUtils.isNotBlank(isExist)) {
                GraceException.display(ResponseStatusEnum.WECHAT_NUM_ALREADY_MODIFIED_ERROR);
            } else {
                // 修改微信二维码
                String qrCodeUrl = getQrCodeUrl(wechatNum, userId);
                updateUser.setWechatNumImg(qrCodeUrl);
            }
        }

        updateUser.setId(userId);
        usersMapper.updateById(updateUser);

        // 如果用户修改微信号，则只能修改一次，放入redis中进行判断
        if (StringUtils.isNotBlank(wechatNum)) {
            redis.setByDays(REDIS_USER_ALREADY_UPDATE_WECHAT_NUM + ":" + userId,
                    userId,
                    365);
        }
    }

    /**
     * 根据用户ID获取用户对象
     *
     * @param userId
     * @return
     */
    @Override
    public Users getUserById(String userId) {
        return usersMapper.selectById(userId);
    }

    private String getQrCodeUrl(String wechatNumber,
                                String userId) {
        try {
            return fileServiceFeign.generatorQrCode(wechatNumber, userId);
        } catch (Exception e) {
            return null;
        }
    }

}
