package com.itchat.service;

import com.itchat.pojo.Users;
import com.itchat.vo.ModifyUserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 王青玄
 * @since 2024-09-07
 */
public interface UsersService {

    /**
     * 修改用户信息
     *
     * @param modifyUserVO 前端传递的用户信息
     */
    void modifyUserInfo(ModifyUserVO modifyUserVO);

    /**
     * 根据用户ID获取用户对象
     * @param userId
     * @return
     */
    Users getUserById(String userId);
}
