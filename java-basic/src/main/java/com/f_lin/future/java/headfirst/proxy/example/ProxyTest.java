package com.f_lin.future.java.headfirst.proxy.example;

import java.lang.reflect.Proxy;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/2
 */
public class ProxyTest {

    public static void main(String[] args) {

        final Animal instance = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class[]{Animal.class}, new JDKSurroundProxy(new Dog()));
        instance.barking();

        ((Animal)new CglibSurroundProxy().getProxy(new Cat())).barking();
    }

}
