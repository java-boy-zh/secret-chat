package com.itchat.wx.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("wx_user")
public class WechatUser {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openId;
    private String nickname;
    private String headImgUrl;
    private Integer subscribe;
    private LocalDateTime subscribeTime;
    private Long userId;  // 关联系统用户ID
}
