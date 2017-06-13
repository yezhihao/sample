package org.sample.commons.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.commons.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Order
@Aspect
@Component
public class MessageHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageHandler.class.getSimpleName());

    /**
     * 切点
     */
    @Pointcut("execution(public * org.sample.controller.*.*(..))")
    public void pointcut() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        StopWatch watch = new StopWatch();
        Object result = point.proceed();
        log.info(point.getSignature().getName() + watch.show());
        return result;
    }

// private static void log(JoinPoint point, Object result) {
// Signature signature = point.getSignature();
//
// StringBuilder sb = new StringBuilder();
// sb.append("接口名:").append(signature.getName());
//
// if (result == null) {
// sb.append("入参:");
// for (Object agr : point.getArgs())
// sb.append(String.valueOf(agr));
// } else {
// sb.append("出参:");
// sb.append(String.valueOf(result));
// }
// log.info(sb.toString());
// }
}