package com.itchat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itchat.common.BaseInfoProperties;
import com.itchat.enums.Sex;
import com.itchat.mapper.UsersMapper;
import com.itchat.pojo.Users;
import com.itchat.service.UsersService;
import com.itchat.utils.DesensitizationUtil;
import com.itchat.utils.LocalDateUtils;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

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

    private static final String USER_FACE1 = "https://i04piccdn.sogoucdn.com/fe4c49fbc39014ff";

    @Resource
    private UsersMapper usersMapper;

    /**
     * 根据手机号查询用户信息
     *
     * @param mobile 手机号
     * @return 用户信息/NULL
     */
    @Override
    public Users queryUserByMobileIfExist(String mobile) {
        LambdaQueryWrapper<Users> query = new LambdaQueryWrapper<>();
        query.eq(Users::getMobile, mobile);
        Users users = usersMapper.selectOne(query);
        return users;
    }

    /**
     * 创建用户信息，并且返回用户对象
     *
     * @param mobile   手机号
     * @param nickname 用户昵称
     * @return 用户信息
     */
    @Override
    @Transactional
    public Users createUsers(String mobile, String nickname) {
        Users user = new Users();
        user.setMobile(mobile);
        if (StringUtils.isBlank(nickname)) {
            nickname = DesensitizationUtil.commonDisplay(mobile);
        }
        user.setNickname(nickname);

        String uuid = UUID.randomUUID().toString();
        String uuidStr[] = uuid.split("-");
        String wechatNum = "secret-chat" + uuidStr[0] + uuidStr[1];
        user.setWechatNum(wechatNum);
        user.setWechatNumImg(USER_FACE1);

        user.setRealName("");
        user.setSex(Sex.secret.type);
        user.setFace(USER_FACE1);
        user.setFriendCircleBg(USER_FACE1);
        user.setEmail("");
        user.setBirthday(LocalDateUtils
                .parseLocalDate("1980-01-01",
                        LocalDateUtils.DATE_PATTERN));

        user.setCountry("中国");
        user.setProvince("");
        user.setCity("");
        user.setDistrict("");

        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(LocalDateTime.now());

        usersMapper.insert(user);

        return user;
    }
}
