package com.itchat.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName IPLimitFilter.java
 * @create 2024年09月04日 下午5:25
 * @Description IP拦截器
 * @Version V1.0
 */
@Component
@Slf4j
public class IPLimitFilter {

    /**
     * 判断某个请求的ip在20秒内的请求次数是否超过3次
     * 如果超过3次，则限制访问30秒
     * 等待30秒静默后，才能够继续恢复访问
     */



}
