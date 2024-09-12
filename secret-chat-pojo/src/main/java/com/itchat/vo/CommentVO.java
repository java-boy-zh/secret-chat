package com.itchat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentVO {

    private String belongUserId;
    private String friendCircleId;

    private String fatherId;

    private String commentUserId;
    private String commentContent;
}
