package com.f_lin.future.java.headfirst.decorate;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 16:33
 **/
public class BeforDecorator extends Decorator {

    public BeforDecorator(Component component) {
        super(component);
    }

    private void before() {

    }

    @Override
    public void operate() {
        before();
        super.operate();
    }

}
