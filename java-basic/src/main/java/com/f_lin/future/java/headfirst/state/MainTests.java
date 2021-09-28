package com.f_lin.future.java.headfirst.state;

import com.f_lin.future.java.headfirst.state.impl.StateContext;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 17:18
 **/
public class MainTests {
    public static void main(String[] args) {
        StateContext context = new StateContext();
        context.setCurrentState(StateContext.state1);

        context.handle1();
        context.handle2();
    }
}
