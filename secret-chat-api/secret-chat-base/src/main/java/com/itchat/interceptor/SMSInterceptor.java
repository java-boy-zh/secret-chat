package com.itchat.interceptor;

import com.itchat.common.BaseInfoProperties;
import com.itchat.exceptions.GraceException;
import com.itchat.result.ResponseStatusEnum;
import com.itchat.utils.IPUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @ClassName SMSInterceptor.java
 * @create 2024年09月06日 上午9:46
 * @Description 短信发送拦截器
 * @Version V1.0
 */
@Slf4j
public class SMSInterceptor extends BaseInfoProperties implements HandlerInterceptor {

    /*前置拦截器-controller 调用之前*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取IP
        String userIp = IPUtil.getRequestIp(request);
        boolean keyIsExist = redis.keyIsExist(MOBILE_SMSCODE + ":" + userIp);
        if (keyIsExist) {
            log.error("短信发送频繁~~~");
            GraceException.display(ResponseStatusEnum.SMS_NEED_WAIT_ERROR);
            return false;
        }

        /**
         * false: 请求被拦截
         * true: 请求正常 放行
         */
        return true;
    }

    /*前置拦截器-controller之后 视图渲染之前*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /*后置拦截器-controller之后 视图渲染之后*/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
