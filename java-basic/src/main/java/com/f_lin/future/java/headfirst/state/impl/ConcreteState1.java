package com.f_lin.future.java.headfirst.state.impl;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 17:07
 **/
public class ConcreteState1 extends State {


    @Override
    public void handle1() {
        //do something this state should done!!!
    }

    @Override
    public void handle2() {
        //change the state to state1. take the method handle1()
        super.context.setCurrentState(StateContext.state2);
        super.context.handle2();
    }
}
