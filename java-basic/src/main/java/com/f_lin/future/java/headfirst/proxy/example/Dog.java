package com.f_lin.future.java.headfirst.proxy.example;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2023/2/2
 */
public class Dog implements Animal{

    @Override
    public void barking() {
        System.out.println("汪汪汪！");
    }

}
