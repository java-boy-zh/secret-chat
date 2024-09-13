package com.itchat.filter;

import com.itchat.common.BaseInfoProperties;
import com.itchat.properties.ExcludeUrlPathProperties;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.ReturnErrorFilterUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName SecurityCheckTokenFilter.java
 * @create 2024年09月04日 下午5:25
 * @Description 校验Token过滤器
 * @Version V1.0
 */
@Component
@Slf4j
public class SecurityCheckTokenFilter extends BaseInfoProperties implements GlobalFilter, Ordered {

    @Resource
    private ExcludeUrlPathProperties excludeUrlPathProperties;

    // 路径匹配对象
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("网关 校验Token过滤器 开始进行处理~~~");
        List<String> urls = excludeUrlPathProperties.getUrls();
        ServerHttpRequest request = exchange.getRequest();
        String requestURI = request.getURI().getPath();
        log.info("当前请求目标地址为{}", requestURI);
        if (!CollectionUtils.isEmpty(urls)) {
            for (String url : urls) {
                boolean matcher = antPathMatcher.matchStart(url, requestURI);
                if (matcher) {
                    // 匹配成功则 往下执行
                    return chain.filter(exchange);
                }
            }
        }
        String fileStart = excludeUrlPathProperties.getFileStart();
        if (StringUtils.isNotBlank(fileStart)) {
            boolean matcher = antPathMatcher.matchStart(fileStart, requestURI);
            if (matcher) {
                // 匹配成功则 往下执行
                return chain.filter(exchange);
            }
        }
        // 目前请求地址未在排除名单中，进行获取用户ID和用户Token操作
        HttpHeaders headers = request.getHeaders();
        String headerUserId = headers.getFirst(HEADER_USER_ID);
        String headerUserToken = headers.getFirst(HEADER_USER_TOKEN);

        log.info("当前请求的用户ID为{}，用户Token为{}", headerUserId, headerUserToken);
        if (StringUtils.isNotBlank(headerUserId)
                && StringUtils.isNotBlank(headerUserToken)) {
//            // 单设备登录
//            String redisUserToken = redis.get(REDIS_USER_TOKEN + ":" + headerUserId);
//            if (headerUserToken.equals(redisUserToken)) {
//                return chain.filter(exchange);
//            }

            // 多设备登录
            String redisUserId = redis.get(REDIS_USER_TOKEN + ":" + headerUserToken);
            if (headerUserId.equals(redisUserId)) {
                return chain.filter(exchange);
            }
        }

        return ReturnErrorFilterUtils.display(exchange, ResponseStatusEnum.UN_LOGIN);
    }

    /*过滤器顺序*/
    /*值越小 越先执行*/
    @Override
    public int getOrder() {
        return 0;
    }

}
