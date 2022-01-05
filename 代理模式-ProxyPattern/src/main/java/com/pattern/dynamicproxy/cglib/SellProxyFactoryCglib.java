package com.pattern.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 王山鹏 (shanpeng.wang@transsion.com)
 * @since 2022-01-05
 */
public class SellProxyFactoryCglib implements MethodInterceptor {


    private static SellProxyFactoryCglib instance = new SellProxyFactoryCglib();

    private SellProxyFactoryCglib(){
    }

    public static SellProxyFactoryCglib getInstance(){
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls){
        return (T) Enhancer.create(cls, this);
    }
    //实现MethodInterceptor接口生成方法拦截器
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before: "  + method.getName());
        Object obj = methodProxy.invokeSuper(o, objects);
        System.out.println("After: " + method.getName());
        return obj;
    }
}
