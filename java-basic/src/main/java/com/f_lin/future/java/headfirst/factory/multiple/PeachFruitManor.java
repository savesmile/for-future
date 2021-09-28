package com.f_lin.future.java.headfirst.factory.multiple;

import com.f_lin.future.java.headfirst.factory.Fruit;
import com.f_lin.future.java.headfirst.factory.fruitimpl.Peach;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:06
 **/
public class PeachFruitManor implements MultipleFruitManor {
    @Override
    public Fruit cultivateFruit() {
        return new Peach();
    }
}
