package com.f_lin.future.java.headfirst.responsibility.eg;

import com.f_lin.future.java.headfirst.responsibility.Chain;
import com.f_lin.future.java.headfirst.responsibility.DefaultRule;
import com.f_lin.future.java.headfirst.responsibility.TestContext;

/**
 * @author F_lin
 * @since 2019/4/12
 **/
public class Rule2 implements DefaultRule {

    @Override
    public void process(TestContext context, Chain chain) {
        System.out.println("rule2 process chain");
    }

    @Override
    public boolean judge(TestContext context) {
        return false;
    }

    @Override
    public String getTag() {
        return "rule2";
    }
}
