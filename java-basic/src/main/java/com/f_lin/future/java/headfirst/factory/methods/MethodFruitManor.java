package com.f_lin.future.java.headfirst.factory.methods;

import com.f_lin.future.java.headfirst.factory.Fruit;
import com.f_lin.future.java.headfirst.factory.FruitManor;

/**
 * 工厂方法模式
 * <p>
 * 水果庄园
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/5 15:54
 **/
public class MethodFruitManor implements FruitManor {

    @Override
    public <T extends Fruit> T cultivateFruit(Class<T> tClass) {
        T t = null;
        try {
            t = tClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

}
