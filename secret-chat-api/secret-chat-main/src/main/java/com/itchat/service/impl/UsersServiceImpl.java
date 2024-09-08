package com.itchat.service.impl;

import com.itchat.common.BaseInfoProperties;
import com.itchat.exceptions.GraceException;
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

    /**
     * 修改用户信息
     *
     * @param modifyUserVO 前端传递的用户信息
     */
    @Override
    @Transactional
    public void modifyUserInfo(ModifyUserVO modifyUserVO) {
        String userId = modifyUserVO.getUserId();
        if (StringUtils.isBlank(userId)) {
            GraceException.display(ResponseStatusEnum.USER_INFO_UPDATED_ERROR);
        }
        Users updateUser = CopyBeanUtils.copy(modifyUserVO, Users.class);
        updateUser.setId(userId);
        usersMapper.updateById(updateUser);
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
}
