package com.f_lin.future.java.headfirst.responsibility;


import java.util.Map;

/**
 * 测试上下文
 *
 * @author F_lin
 * @since 2019/4/12
 **/
public class TestContext {
    /**
     * 标识。或者有其他比较合适的实现方法
     */
    private String tag;
    /**
     * 目标
     */
    private Object target;
    /**
     * 附加值
     */
    private Map<String, Object> extra;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public TestContext(Object params) {
        target = params;
    }
}
