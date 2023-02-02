package com.f_lin.future.java.headfirst.decorate;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 16:33
 **/
public class SurroundDecorator extends Decorator {

    public SurroundDecorator(Component component) {
        super(component);
    }

    private void before() {
    }

    private void after() {
    }

    @Override
    public void operate() {
        before();
        super.operate();
        after();
    }

}
