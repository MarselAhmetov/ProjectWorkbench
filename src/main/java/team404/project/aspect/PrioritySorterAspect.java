package team404.project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PrioritySorterAspect {

    @Around(value= "execution(* team404.project.service.implementations.DebtsPrioritySorterImpl.sortByPriority(..))")
    public Object logSignUp(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println(timeTaken);
        return object;
    }

    @Before(value= "execution(* team404.project.service.implementations.ConfirmServiceImpl.confirm(..))")
    public void logConfirm(JoinPoint joinPoint) {
        System.out.println("User confirmed");
    }
}
