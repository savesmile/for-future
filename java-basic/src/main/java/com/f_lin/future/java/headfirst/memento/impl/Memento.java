package com.f_lin.future.java.headfirst.memento.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 备忘录，用于备份目标状态等属性
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 15:36
 **/
@Setter
@Getter
@AllArgsConstructor
public class Memento {

    private String status;
}
