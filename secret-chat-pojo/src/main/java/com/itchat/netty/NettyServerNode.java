package com.itchat.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Auther 王青玄
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NettyServerNode {

    private String ip;
    private Integer port;
    private Integer onlineCounts = 0;

}
