package com.f_lin.future.java.headfirst.builder;

/**
 * 建造对象
 * 果园
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:46
 **/
public interface Manor {

    /**
     * 不同工厂工作流程
     */
    default void work() {
        if ("orange".equals(fruitType())) {
            weeding();
            arableLand();
            reward();
        } else if ("apple".equals(fruitType())) {
            arableLand();
            reward();
        }
    }

    String fruitType();

    /**
     * 主人
     */
    String master();

    /**
     * 除草
     */
    void weeding();

    /**
     * 耕地
     */
    void arableLand();

    /**
     * 收获
     */
    void reward();

}
