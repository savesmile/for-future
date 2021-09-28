package com.f_lin.future.java.headfirst.responsibility;

import com.f_lin.future.java.headfirst.responsibility.exception.ChainProcessException;

/**
 * @author F_lin
 * @since 2019/4/12
 **/
public interface DefaultRule extends Tag, Judger {

    /**
     * 此规则是否可以消费此上下文
     * 藉此可以进行简单的规则筛选
     *
     * @param context 上下文
     * @param chain   责任链
     */
    default void consume(TestContext context, Chain chain) throws ChainProcessException {
        if (context.getTag().equals(getTag()) && judge(context)) {
            process(context, chain);
        } else {
            chain.doChain(context);
        }
    }

    /**
     * 处理
     */
    void process(TestContext context, Chain chain) throws ChainProcessException;
}
