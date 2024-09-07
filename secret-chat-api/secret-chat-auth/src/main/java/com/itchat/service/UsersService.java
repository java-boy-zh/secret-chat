package com.itchat.service;

import com.itchat.pojo.Users;

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
     * 根据手机号查询用户信息
     *
     * @param mobile 手机号
     * @return 用户信息/NULL
     */
    Users queryUserByMobileIfExist(String mobile);

    /**
     * 创建用户信息，并且返回用户对象
     *
     * @param mobile   手机号
     * @param nickname 用户昵称
     * @return 用户信息
     */
    Users createUsers(String mobile, String nickname);

}
