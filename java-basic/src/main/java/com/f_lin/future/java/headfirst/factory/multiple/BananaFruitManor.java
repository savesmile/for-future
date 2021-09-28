package com.f_lin.future.java.headfirst.factory.multiple;

import com.f_lin.future.java.headfirst.factory.Fruit;
import com.f_lin.future.java.headfirst.factory.fruitimpl.Banana;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:05
 **/
public class BananaFruitManor implements MultipleFruitManor {
    @Override
    public Fruit cultivateFruit() {
        return new Banana();
    }
}
