package com.f_lin.future.java.headfirst.decorate;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 16:33
 **/
public class AfterDecorator extends Decorator {

    public AfterDecorator(Component component) {
        super(component);
    }

    private void after() {

    }

    @Override
    public void operate() {
        super.operate();
        after();
    }

}
