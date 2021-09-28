package com.f_lin.future.java.headfirst.memento.impl;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 15:35
 **/
@Setter
@Getter
public class Originator {

    /**
     * 需要保存的状态
     */
    private String status;

    /**
     * 创建副本
     */
    public Memento createMemento() {
        return new Memento(this.status);
    }

    /**
     * 从某个副本中恢复属性
     *
     * @param memento 副本
     */
    public void restoreMemento(Memento memento) {
        this.setStatus(memento.getStatus());
    }
}
