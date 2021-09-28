package com.f_lin.future.java.headfirst.state.impl;

/**
 * 环境角色有两个不成文的约束：
 * ● 把状态对象声明为静态常量，有几个状态对象就声明几个静态常量。
 * ● 环境角色具有状态抽象角色定义的所有行为，具体执行使用委托方式。
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 17:06
 **/
public class StateContext {
    /**
     * 状态1
     */
    public static final State state1 = new ConcreteState1();
    /**
     * 状态2
     */
    public static final State state2 = new ConcreteState2();

    private State currentState;

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * 将 State中的方法委托到环境中来
     */
    public void handle1() {
        currentState.handle1();
    }

    public void handle2() {
        currentState.handle2();
    }
}
