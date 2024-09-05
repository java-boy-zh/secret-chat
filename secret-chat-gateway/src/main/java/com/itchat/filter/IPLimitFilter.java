package com.itchat.filter;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
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
public class IPLimitFilter implements GlobalFilter, Ordered {

    /**
     * 判断某个请求的ip在20秒内的请求次数是否超过3次
     * 如果超过3次，则限制访问30秒
     * 等待30秒静默后，才能够继续恢复访问
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("网关 IP拦截器 开始进行拦截~~~");
        // 获取到请求ip地址
        ServerHttpRequest request = exchange.getRequest();
        String ip = IPUtil.getIP(request);
        if (1 == 1) {
            return renderErrorMsg(exchange,ResponseStatusEnum.SYSTEM_ERROR_BLACK_IP);
        }
        // 放行到后续路由(服务)
        return chain.filter(exchange);
    }

    /**
     * 重新包装并返回错误信息
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
        if (!response.getHeaders().containsKey("Content-Type")){
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
