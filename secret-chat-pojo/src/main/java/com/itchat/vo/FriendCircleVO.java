package com.itchat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FriendCircleVO {

    private String userId;
    private String words;
    private String images;
    private String video;
    private LocalDateTime publishTime;

}
