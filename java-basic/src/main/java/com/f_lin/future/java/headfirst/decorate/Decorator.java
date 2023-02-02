package com.f_lin.future.java.headfirst.decorate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 16:17
 **/
@AllArgsConstructor
@NoArgsConstructor
public abstract class Decorator implements Component {

    private Component component;

    @Override
    public void operate() {
        component.operate();
    }

}


