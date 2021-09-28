package com.f_lin.future.java.headfirst.adapter;

/**
 * Adapter 该适配器可以当作 Target 以及 SourceTarget 使用。注入。实际执行逻辑可以自由选择
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 17:59
 **/
public class Adapter extends SourceTarget implements Target {

    void doSomething() {

    }

    @Override
    public void action() {
        super.beforeAction();
        doSomething();
    }

}
