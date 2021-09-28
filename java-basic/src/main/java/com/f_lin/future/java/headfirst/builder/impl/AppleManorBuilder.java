package com.f_lin.future.java.headfirst.builder.impl;

import com.f_lin.future.java.headfirst.builder.IManorBuilder;
import com.f_lin.future.java.headfirst.builder.Manor;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:56
 **/
public class AppleManorBuilder implements IManorBuilder {

    @Override
    public Manor builderManor(String masterName) {
        return new AppleManor(masterName);
    }
}
