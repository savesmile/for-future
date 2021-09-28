package com.f_lin.future.java.headfirst.memento.impl;

import lombok.Getter;
import lombok.Setter;

/**

 * 副本管理者，负责调用创建副本
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 15:39
 **/
@Setter
@Getter
public class MemManager {
    //备忘录对象
    private Memento memento;

}

