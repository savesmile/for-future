package com.f_lin.future.java.basic.rawtype;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/11/29
 */
public class SubClass extends TopRawClass<String,Integer> {

    public static void main(String[] args) {
        System.out.println(TopRawClass.getRawType(SubClass.class, 1));
    }
}
