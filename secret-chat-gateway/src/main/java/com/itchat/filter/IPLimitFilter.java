package com.itchat.filter;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

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
                continueCounts,timeInterval,limintTimes);

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
            return renderErrorMsg(exchange, ResponseStatusEnum.SYSTEM_ERROR_BLACK_IP);
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
            return renderErrorMsg(exchange, ResponseStatusEnum.SYSTEM_ERROR_BLACK_IP);
        }
        // 放行到后续路由(服务)
        return chain.filter(exchange);
    }


    /**
     * 重新包装并返回错误信息
     *
     * @param exchange
     * @param responseStatusEnum
     * @return
     */
    private Mono<Void> renderErrorMsg(ServerWebExchange exchange,
                                      ResponseStatusEnum responseStatusEnum) {
        // 获得响应
        ServerHttpResponse response = exchange.getResponse();
        // 构建返回对象
        GraceJSONResult jsonResult = GraceJSONResult.exception(responseStatusEnum);
        // 设置返回header类型
        if (!response.getHeaders().containsKey("Content-Type")) {
            response.getHeaders().add("Content-Type",
                    MimeTypeUtils.APPLICATION_JSON_VALUE);
        }
        // 将response的状态码改为500
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        // 将返回对象转为json，并将该信息放置到response
        String result = new Gson().toJson(jsonResult);
        DataBuffer buffer =
                response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }

    /*过滤器顺序*/
    /*值越小 越先执行*/
    @Override
    public int getOrder() {
        return 1;
    }

}
