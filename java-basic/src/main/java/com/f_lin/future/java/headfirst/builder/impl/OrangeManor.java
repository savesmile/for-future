package com.f_lin.future.java.headfirst.builder.impl;

import com.f_lin.future.java.headfirst.builder.Manor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:48
 **/
@AllArgsConstructor
@NoArgsConstructor
public class OrangeManor implements Manor {

    private String materName;

    @Override
    public String fruitType() {
        return "orange";
    }

    @Override
    public String master() {
        return materName;
    }

    @Override
    public void weeding() {
        System.out.println(master() + "开始除草");
    }

    @Override
    public void arableLand() {
        System.out.println(master() + "开始耕地");
    }

    @Override
    public void reward() {
        System.out.println(master() + "收获了 8斤橘子");
    }
}
