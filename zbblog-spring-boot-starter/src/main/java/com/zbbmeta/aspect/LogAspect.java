package com.zbbmeta.aspect;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.zbbmeta.dto.CommonLog;
import com.zbbmeta.util.IPUtil;
import com.zbbmeta.util.RequestHolder;
import com.zbbmeta.util.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.MethodParameter;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.annotation.SynthesizingMethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author springboot葵花宝典
 * @description: 日志拦截器
 */

@Slf4j
@Aspect
public class LogAspect {


//    @Pointcut("@annotation(com.zbbmeta.annotation.Log)")
    @Pointcut("execution(* *..*Controller.*(..))")
    public void pointcut() {
    }

    /**
     * 环绕通知，使用Pointcut()上注册的切入点
     * @param point
     * @return
     */
    @Around("pointcut()")
    public Object recordLog(ProceedingJoinPoint point) throws Throwable {
        Object result = new Object();

        //　获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();


        // 判断为空则直接跳过执行
        if (ObjectUtils.isEmpty(request)){
            return point.proceed();
        }
        //　获取注解里的value值
        Method targetMethod = resolveMethod(point);
//        Log logAnn = targetMethod.getAnnotation(Log.class);
        // 打印执行时间
        long startTime = System.nanoTime();
        // 请求方法
        String method = request.getMethod();
        String url = request.getRequestURI();

        // 获取IP和地区
        String ip = RequestHolder.getHttpServletRequestIpAddress();
        String region = IPUtil.getCityInfo(ip);

        //获取请求参数
        //Map<String, Object> paramMap = logIngArgs(point);
        // 参数
        Object[] args = point.getArgs();
        String requestParam = getArgs(args, request);

        // 计算耗时
        long tookTime = 0L;
        try {
            result = point.proceed();
        } finally {
            tookTime = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime);
        }
        //　如果是登录请求，则不获取用户信息
        String userName = "springboot葵花宝典";

        //　封装SysLog
        CommonLog commonLog = new CommonLog();
        commonLog.setIp(ip)
                .setCreateBy(userName)
                .setMethod(method)
                .setUrl(url)
                .setOperation(String.valueOf(result))
                .setLocation(StringUtils.isEmpty(region) ? "本地" : region)
                .setExecuteTime(tookTime)
//                .setTitle(logAnn.value())
                .setParams(JSON.toJSONString(requestParam));
        log.info("Http Request: {}", JSONObject.toJSONString(commonLog));


        return result;
    }

    /**
     * 配置异常通知
     *
     * @param point join point for advice
     * @param e exception
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint point, Throwable e) {
        // 打印执行时间
        long startTime = System.nanoTime();

        CommonLog commonLog = new CommonLog();

        // 获取IP和地区
        String ip = RequestHolder.getHttpServletRequestIpAddress();
        String region = IPUtil.getCityInfo(ip);


        //　获取request
        HttpServletRequest request = RequestHolder.getHttpServletRequest();

        // 请求方法
        String method = request.getMethod();
        String url = request.getRequestURI();

        //　获取注解里的value值
        Method targetMethod = resolveMethod((ProceedingJoinPoint) point);
//        Log logAnn = targetMethod.getAnnotation(Log.class);

        commonLog.setExecuteTime(TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime))
                .setIp(ip)
                .setLocation(region)
                .setMethod(method)
                .setUrl(url)
                .setType("2")
//                .setTitle(logAnn.value())
                .setException(getStackTrace(e));
        // 发布事件
        log.info("Error Result: {}", commonLog);
    }

    private Method resolveMethod(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();

        Method method = getDeclaredMethod(targetClass, signature.getName(),
                signature.getMethod().getParameterTypes());
        if (method == null) {
            throw new IllegalStateException("无法解析目标方法: " + signature.getMethod().getName());
        }
        return method;
    }

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

    private Method getDeclaredMethod(Class<?> clazz, String name, Class<?>... parameterTypes) {
        try {
            return clazz.getDeclaredMethod(name, parameterTypes);
        } catch (NoSuchMethodException e) {
            Class<?> superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getDeclaredMethod(superClass, name, parameterTypes);
            }
        }
        return null;
    }

    /**
     * 获取请求参数
     * @param args
     * @param request
     * @return
     */
    private String getArgs(Object[] args, HttpServletRequest request) {
        String strArgs = StringPool.EMPTY;

        try {
            if (!request.getContentType().contains("multipart/form-data")) {
                strArgs = JSONObject.toJSONString(args);
            }
        } catch (Exception e) {
            try {
                strArgs = Arrays.toString(args);
            } catch (Exception ex) {
                log.warn("解析参数异常", ex);
            }
        }
        return strArgs;
    }


}
