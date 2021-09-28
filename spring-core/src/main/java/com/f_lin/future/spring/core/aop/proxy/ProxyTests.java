package com.f_lin.future.spring.core.aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author F_lin
 * @since 2019/4/15
 **/
public class ProxyTests {

    public static void main(String[] args) {
        Person agent = (Person)agent(Person.class, new Tom());

        agent.say();
        agent.dadad();
    }


    public interface Person {
        void say();

        void dadad();
    }

    public static class Tom implements Person{
        public Tom() {
        }

        @Override
        public void say() {
            System.out.println("tom said: you are stupid");
        }

        @Override
        public void dadad() {
            System.out.println("111");
        }
    }

    //实现InvocationHandler接口，并且可以初始化被代理类的对象
    static class MyHandler implements InvocationHandler {
        private Object proxy;
        public MyHandler(Object proxy) {
            this.proxy = proxy;
        }

        //自定义invoke方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("hello boy, please saying!");
            //真正调用方法的地方
            Object ret = method.invoke(this.proxy, args);

            System.out.println("well! nice toking");
            return ret;
        }
    }

    //返回一个被修改过的对象
    public static Object agent(Class interfaceClazz, Object proxy) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        return Proxy.newProxyInstance(interfaceClazz.getClassLoader(), new Class[]{interfaceClazz},
                new MyHandler(proxy));
    }

}
