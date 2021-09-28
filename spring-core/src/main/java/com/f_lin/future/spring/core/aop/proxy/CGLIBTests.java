package com.f_lin.future.spring.core.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author F_lin
 * @since 2019/4/15
 **/
public class CGLIBTests implements MethodInterceptor {

    private Object proxy;

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("hello boy, please saying!");
        //真正调用方法的地方
        Object ret = methodProxy.invokeSuper(o, objects);
        System.out.println("well! nice toking");
        return ret;
    }


    public static void main(String[] args) {
        CGLIBTests cGlibAgent = new CGLIBTests();
        ProxyTests.Tom apple = (ProxyTests.Tom) cGlibAgent.getInstance(new ProxyTests.Tom());
        apple.say();

    }
}
