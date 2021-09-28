package com.f_lin.future.java.headfirst.strategy;

/**
 * 策略枚举
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 17:52
 **/
public enum StrategyEnum {

    /**
     * 加法运算
     */
    ADD("+") {
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    /**
     * 减法运算
     */
    SUB("-") {
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };
    String value;

    //定义成员值类型
    StrategyEnum(String _value) {
        this.value = _value;
    }

    //获得枚举成员的值
    public String getValue() {
        return this.value;
    }

    //声明一个抽象函数
    public abstract int exec(int a, int b);
}
