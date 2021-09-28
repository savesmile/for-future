package com.f_lin.future.java.headfirst.factory.fruitimpl;

import com.f_lin.future.java.headfirst.factory.Fruit;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/5 15:50
 **/
public class Banana implements Fruit {
    @Override
    public String name() {
        return "banana";
    }

    @Override
    public String color() {
        return "yellow";
    }
}
