package tech.starter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {

    @Pointcut("within(@tech.starter.Timer *)")
    public void beansMethod() {}

    @Pointcut("@annotation(tech.starter.Timer)")
    public void beansWithAnnotation() {}

    @Around("beansWithAnnotation()")
    public Object getTime(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = point.proceed();

        long elapsedTime = System.currentTimeMillis() - start;

        System.out.println("Класс: " + point.getClass().getName() + ", метод: " + point.getSignature().getName() +
                "() выполнился за " + elapsedTime + " миллисекунд");
        return result;
    }
}