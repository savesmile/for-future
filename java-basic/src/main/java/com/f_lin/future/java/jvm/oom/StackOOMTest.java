package com.f_lin.future.java.jvm.oom;

/**
 * -Xss180k
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2022/3/3
 */
public class StackOOMTest {


    static int i = 0;

    public static void main(String[] args) {
        test();
    }

    static void test() {
        System.out.println("deep: ["+(++i)+"]");
        int x1,x2,x3,x4;
        test();
        x1 = x2 = x3 = x4 = 1;
    }

}
