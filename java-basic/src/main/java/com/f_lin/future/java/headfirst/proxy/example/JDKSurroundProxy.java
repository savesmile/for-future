package com.f_lin.future.java.headfirst.proxy.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/2
 */
public class JDKSurroundProxy<T> implements InvocationHandler {

    private T target;

    public JDKSurroundProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法执行前。。。。");
        final Object invoke = method.invoke(target, args);
        System.out.println("代理方法执行后。。。。");
        return invoke;
    }
}
