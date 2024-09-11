package com.itchat.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContactsBO implements Serializable {

    private String friendshipId;
    private String mySelfId;
    private String myFriendId;
    private String myFriendFace;
    private String myFriendNickname;
    private String myFriendRemark;
    private String chatBg;
    private Integer isMsgIgnore;
    private Integer isBlack;

}