package com.f_lin.future.java.headfirst.proxy.example;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/2
 */
public class CglibSurroundProxy<T> implements MethodInterceptor {

    private Enhancer eh = new Enhancer();
    private T target;

    public T getProxy(T target) {
        this.target = target;
        eh.setSuperclass(target.getClass());
        eh.setCallback(this);
        return (T) eh.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("代理方法执行前。。。。");
        final Object invoke = methodProxy.invoke(target, args);
        System.out.println("代理方法执行后。。。。");
        return invoke;
    }
}
