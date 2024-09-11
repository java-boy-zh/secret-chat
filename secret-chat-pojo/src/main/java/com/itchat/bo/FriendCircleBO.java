package com.itchat.bo;

import com.itchat.pojo.FriendCircleLiked;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendCircleBO implements Serializable {

    private String friendCircleId;
    private String userId;
    private String userNickname;
    private String userFace;
    private String words;
    private String images;
    private String video;
    private LocalDateTime publishTime;

    private List<FriendCircleLiked> likedFriends = new ArrayList<>();   // 点赞的朋友列表
    private Boolean doILike = false;                                    // 用于判断当前用户是否点赞过朋友圈

    private List<CommentBO> commentList = new ArrayList<>();            // 朋友圈的评论列表

}
