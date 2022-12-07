package com.myportfolio.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAdvice { // Advice에도 @Component가 붙어야 하고, @Aspect도 필요하다
    @Around("execution(* com.myportfolio.web.aop.MyMath.add*(..))") // pointcut - 패턴에 맞는 클래스의 메서드에 Around Advice가 적용된다
    public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable { // 부가기능이 포함된 메서드
        long start = System.currentTimeMillis();
        System.out.println("<<[start] " + pjp.getSignature().getName() + Arrays.toString(pjp.getArgs()));

        Object result = pjp.proceed(); // target의 메서드를 호출

        System.out.println("result=" + result);
        System.out.println("[end]>>" + (System.currentTimeMillis() - start) + "ms");
        return result;
    }
}
