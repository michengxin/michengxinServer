package org.springboot.Aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springboot.annotation.CherryAnnotation;
import org.springboot.config.ResponseData.clas.RestResponseData;
import org.springframework.stereotype.Component;

/**
 * @ClassName CherryAspect
 * @Description TODO
 * @Author mcx
 * @Date 2020/7/28 9:32
 * @Version 1.0
 */
@Aspect
@Component
@Slf4j
public class CherryAspect {
//    任意公共方法的执行：execution(public * *(..))
//    任何一个以“set”开始的方法的执行：execution(* set*(..))
//    AccountService 接口的任意方法的执行：execution(* com.xyz.service.AccountService.*(..))
//    定义在service包里的任意方法的执行： execution(* com.xyz.service.*.*(..))
//    定义在service包和所有子包里的任意类的任意方法的执行：execution(* com.xyz.service..*.*(..))
//    第一个*表示匹配任意的方法返回值， ..(两个点)表示零个或多个，
//    第一个..表示service包及其子包,第二个*表示所有类, 第三个*表示所有方法，第二个..表示方法的任意参数个数

    @Around("execution(* org.springboot.controller.*.*(..)) && @annotation(cherryAnnotation)")
    public RestResponseData Around(ProceedingJoinPoint pjp, CherryAnnotation cherryAnnotation) {
        System.out.println(cherryAnnotation);//注解里面的值
        System.out.println(cherryAnnotation.name());
        Object[] args = pjp.getArgs(); //参数
        if(args.length>0) {
            System.out.println(args[0]);
        }
        Object proceed = null;
        RestResponseData restResponseData = new RestResponseData();
        restResponseData.setCode(500);
        restResponseData.setMessage("执行异常");
        try {
            proceed = pjp.proceed(args);
            ObjectMapper objectMapper = new ObjectMapper();
            restResponseData = objectMapper.convertValue(proceed, RestResponseData.class);
            System.out.println(restResponseData); //返回值

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            return restResponseData;
        }
    }
    @AfterReturning(value = "execution(* org.springboot.controller.*.*(..)) && @annotation(org.springboot.annotation.CherryAnnotation)", returning = "value")
    public void AfterReturning(JoinPoint joinPoint, Object value) {
        System.out.println(joinPoint);
        System.out.println(value);
    }
    //标识一个前置增强方法，相当于BeforeAdvice的功能
//    @Before(value = "execution(* org.springboot.controller.*.*(..)) && @annotation(org.springboot.annotation.CherryAnnotation)",  returning = "value")
//    public void Before(JoinPoint joinPoint, Object value) {
//        System.out.println("Before"+value);
//    }
    //final增强，不管是抛出异常或者正常退出都会执行。
    @After(value = "execution(* org.springboot.controller.*.*(..)) && @annotation(org.springboot.annotation.CherryAnnotation)")
    public void After(JoinPoint joinPoint) {
        System.out.println("After");
        System.out.println(joinPoint);
    }
    //异常抛出增强，相当于ThrowsAdvice
    @AfterThrowing(value = "execution(* org.springboot.controller.*.*(..)) && @annotation(org.springboot.annotation.CherryAnnotation)")
    public void AfterThrowing(JoinPoint joinPoint) {
        System.out.println("AfterThrowing");
        System.out.println(joinPoint);
    }
    // 环绕增强，相当于MethodInterceptor
//    @Around(value = "execution(* org.springboot.controller.*.*(..)) && @annotation(org.springboot.annotation.CherryAnnotation)")
//    public void Around(JoinPoint joinPoint) {
//        System.out.println("Around");
//        System.out.println(joinPoint);
//    }

}