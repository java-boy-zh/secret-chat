package com.itchat.utils;

import com.alibaba.nacos.shaded.com.google.gson.Gson;
import com.itchat.common.BaseInfoProperties;
import com.itchat.result.GraceJSONResult;
import com.itchat.result.ResponseStatusEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName IPLimitFilter.java
 * @create 2024年09月04日 下午5:25
 * @Description IP拦截器
 * @Version V1.0
 */
public class ReturnErrorFilterUtils extends BaseInfoProperties {
    /**
     * 重新包装并返回错误信息
     *
     * @param exchange
     * @param responseStatusEnum
     * @return
     */
    public static Mono<Void> display(ServerWebExchange exchange,
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
}
