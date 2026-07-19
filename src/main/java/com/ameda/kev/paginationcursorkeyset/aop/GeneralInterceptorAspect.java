package com.ameda.kev.paginationcursorkeyset.aop;

import com.ameda.kev.paginationcursorkeyset.dto.CursorPageResponse;
import com.ameda.kev.paginationcursorkeyset.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Author: kev.Ameda
 */
@Aspect
@Slf4j
@Component
public class GeneralInterceptorAspect {

    /**
    * Basically any type  * <br/>
     * Class Name * <br/>
     * Method Name * <br/>
     * Arguments .. <br/>
    * */
    @Pointcut(value = "execution(* com.ameda.kev.paginationcursorkeyset.resource.*.*(..))")
    public void loggingPointcut(){}

//    @Before("loggingPointcut()")
//    public void before(JoinPoint joinPoint){
//        log.info("Before  method invocation: {}", joinPoint.getSignature());
//    }
//
//    @After("loggingPointcut()")
//    public void after(JoinPoint joinPoint){
//        log.info("After  method invocation: {}", joinPoint.getSignature());
//    }

//    @AfterReturning(value = "execution(* com.ameda.kev.paginationcursorkeyset.resource.*.*(..))",
//            returning = "cursorPageResponse")
//    public void afterReturning(JoinPoint joinPoint, ResponseEntity<CursorPageResponse<User>> cursorPageResponse){
//        cursorPageResponse.getBody()
//                .data()
//                .stream()
//                .map( user -> user.getId())
//                .forEach( userId ->
//                        log.info("Retrieved user Id ==> {}",userId));
//    }
//
//    @AfterThrowing(value = "execution(* com.ameda.kev.paginationcursorkeyset.resource.*.*(..))",
//            throwing = "ex")
//    public void afterThrowing(JoinPoint joinPoint, Throwable ex){
//        log.info("After throwing: {}", ex.getMessage());
//    }


//    @Around(value = "execution(* com.ameda.kev.paginationcursorkeyset.resource.*.*(..))")
//    @Around(value = "within(com.ameda.kev.paginationcursorkeyset.service.*)")
//    @Around(value = "this(com.ameda.kev.paginationcursorkeyset.service.UserService)")
    @Around(value = "@annotation(com.ameda.kev.paginationcursorkeyset.annotations.CustomAnnotation)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        log.info("Before method invoked: {}",joinPoint.getArgs()[0]);
        Object object = joinPoint.proceed();
        if( object instanceof ResponseEntity<?>){
            log.info("After method invoked:{}", joinPoint.getArgs()[0]);
        }
        return object;
    }

}
