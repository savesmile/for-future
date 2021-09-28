package com.f_lin.future.java.headfirst.strategy;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 17:48
 **/
public class StrateryContext {

    private Strategy strategy;

    public StrateryContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        this.strategy.execute();
    }
}
