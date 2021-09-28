package com.f_lin.future.java.headfirst.state.impl;

/**
 * 环境角色
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 17:06
 **/
public abstract class State {

    protected StateContext context;

    public abstract void handle1();

    public abstract void handle2();
}
