package com.itchat.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.concurrent.Executors;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName ServiceLogAspect.java
 * @create 2024年09月08日 下午4:19
 * @Description 业务层统一日志拦截器
 * @Version V1.0
 */
@Component
@Slf4j
@Aspect
public class ServiceLogAspect {

    @Around("execution(* com.itchat.service.impl..*(..))")
    public Object taskExecutorTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 构建 service 执行的 方法名及路径
        String servicePathAndMethodName = joinPoint.getTarget().getClass().getName()
                + "."
                + joinPoint.getSignature().getName();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object proceed = joinPoint.proceed();
        stopWatch.stop();

        long takeTimes = stopWatch.getTotalTimeMillis();
        if (takeTimes > 3000) {
            log.error("执行位置{},执行时间太长，耗费了{}毫秒", servicePathAndMethodName, takeTimes);
        } else if (takeTimes > 2000) {
            log.warn("执行位置{},执行时间比较长，耗费了{}毫秒", servicePathAndMethodName, takeTimes);
        } else {
            log.info("执行位置{},执行时间正常，耗费了{}毫秒", servicePathAndMethodName, takeTimes);
        }

        return proceed;
    }

}
