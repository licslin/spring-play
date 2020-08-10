package org.licslan.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author LICSLAN
 * */
@Aspect
public class LogAspects {

    //切入点表达式  抽出公共的切入点表达式
    @Pointcut("execution(public int org.licslan.aop.MathCount.*(..))")
    public void pointcut(){

    }

    //目标方法之前切入 切入点表达式
    @Before("pointcut()") //本类引用
    //@Before("public int org.licslan.aop.MathCount.*(..)")
    //@Before("public int org.licslan.aop.MathCount.div(int,int)")
    public void logstrat(){
        System.out.println("start....div  方法计算开始");
    }

    @Before("pointcut()") //本类引用
    //@Before("public int org.licslan.aop.MathCount.*(..)")
    //@Before("public int org.licslan.aop.MathCount.div(int,int)")

    //JoinPoint joinPoint 参数一定一定写在参数列表前面 不然spring不会识别
    public void logstrat(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        System.out.println("start....div  方法计算开始，方法参数是： "+ Arrays.asList(args)+"==== 方法名称是： "+joinPoint.getSignature().getName());
    }

    //其他类引用使用全路径
    @After("org.licslan.aop.LogAspects.pointcut()")
    public void logEnd(){
        System.out.println("end....div 方法调用结束");
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void logReturn(){
        System.out.println("return....div 返回结果");
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void logReturn(Object result){
        System.out.println("return....div 返回结果: "+ result);
    }

    @AfterThrowing(value = "pointcut()",throwing = "exception")
    public void logError(Exception exception){
        System.out.println("error....div  方式出现异常"+exception);
    }
}

