package org.sample.commons.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.sample.model.BaseVO;
import org.sample.exception.APIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

@Order
@Aspect
// @Component
/** 切面 */
public class MessageHandler {

    private static final Logger log = LoggerFactory.getLogger(MessageHandler.class.getSimpleName());

    /** 切点 */
    @Pointcut("execution(public * org.sample.service.*.*(..))")
    public void pointcut() {
    }

    /** 通知 */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) {
        try {
            return point.proceed();
        } catch (APIException e) {
            log.debug("业务异常:", e);
            return new BaseVO(e);
        } catch (Throwable e) {
            log.error("系统出错:", e);
            return new BaseVO(e);
        }
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