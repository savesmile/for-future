package com.f_lin.future.java.headfirst.factory;

/**
 * 水果庄园
 * 培育不同的水果
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/5 15:46
 **/
public interface FruitManor {

    <T extends Fruit> T cultivateFruit(Class<T> tClass);

}
