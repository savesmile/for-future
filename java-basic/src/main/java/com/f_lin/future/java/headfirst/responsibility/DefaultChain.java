package com.f_lin.future.java.headfirst.responsibility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 默认责任链规则
 *
 * @author F_lin
 * @since 2019/4/12
 **/
public final class DefaultChain implements Chain {

    private final Iterator<DefaultRule> iterator;

    private DefaultChain(List<DefaultRule> rules) {
        iterator = new ArrayList<>(rules).iterator();
    }

    public static DefaultChain of(List<DefaultRule> rules) {
        return new DefaultChain(rules);
    }

    @Override
    public void doChain(TestContext context) {
        if (iterator.hasNext()) {
            iterator.next().consume(context, this);
        }
    }
}
