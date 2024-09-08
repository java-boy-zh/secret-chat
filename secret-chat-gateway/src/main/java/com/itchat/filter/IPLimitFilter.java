package com.itchat.filter;

import com.itchat.common.BaseInfoProperties;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.IPUtil;
import com.itchat.utils.ReturnErrorFilterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName IPLimitFilter.java
 * @create 2024年09月04日 下午5:25
 * @Description IP拦截器
 * @Version V1.0
 */
@Component
@Slf4j
@RefreshScope
public class IPLimitFilter extends BaseInfoProperties implements GlobalFilter, Ordered {

    /**
     * 判断某个请求的ip在20秒内的请求次数是否超过3次
     * 如果超过3次，则限制访问30秒
     * 等待30秒静默后，才能够继续恢复访问
     */
    @Value("${blackIp.continueCounts}")
    private Integer continueCounts;
    @Value("${blackIp.timeInterval}")
    private Integer timeInterval;
    @Value("${blackIp.limintTimes}")
    private Integer limintTimes;

    private static final String ipRedisKey = "secret:chat:gateway:ip";
    private static final String ipRedisLimitKey = "secret:chat:gateway:ip:limit";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("网关 IP拦截器 开始进行拦截~~~");
        log.info("continueCounts->{},timeInterval->{},limintTimes->{}",
                continueCounts, timeInterval, limintTimes);

        return doLimit(exchange, chain);
    }

    /**
     * 限制ip请求次数的判断
     *
     * @param exchange
     * @param chain
     * @return
     */
    private Mono<Void> doLimit(ServerWebExchange exchange,
                               GatewayFilterChain chain) {
        // 获取到请求ip地址
        ServerHttpRequest request = exchange.getRequest();
        String ip = IPUtil.getIP(request);
        // 正常/黑名单 存放至redis中的键
        String writeIp = ipRedisKey + ip;
        String blackIp = ipRedisLimitKey + ip;
        // 获取当前IP，并查询还剩余多少时间 如果时间大于0 则表示还处在黑名单中
        long limitTimes = redis.ttl(blackIp);
        if (limitTimes > 0) {
            // 中止请求
            return ReturnErrorFilterUtils.display(exchange, ResponseStatusEnum.SYSTEM_ERROR_BLACK_IP);
        }
        // 在 redis中对该key进行累加1
        long writeIpConut = redis.increment(writeIp, 1);
        // 第一次进来为1 ， 每隔20秒 只能请求3次
        if (writeIpConut == 1) {
            redis.expire(writeIp, timeInterval);
        }
        // 如果大于3 限制IP
        if (writeIpConut >= continueCounts) {
            redis.set(blackIp, blackIp, limintTimes);
            // 中止请求
            return ReturnErrorFilterUtils.display(exchange, ResponseStatusEnum.SYSTEM_ERROR_BLACK_IP);
        }
        // 放行到后续路由(服务)
        return chain.filter(exchange);
    }

    /*过滤器顺序*/
    /*值越小 越先执行*/
    @Override
    public int getOrder() {
        return 1;
    }

}
