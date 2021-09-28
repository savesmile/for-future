package com.f_lin.future.java.headfirst.builder;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 11:07
 **/
public class MainTests {

    public static void main(String[] args) {
        BuilderDirector builder = new BuilderDirector();
        Manor appleManorOfLaLa = builder.getAppleManorOfLaLa();
        appleManorOfLaLa.work();

        Manor orangeManorOfDADA = builder.getOrangeManorOfDADA();
        orangeManorOfDADA.work();
    }
}
