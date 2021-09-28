package com.f_lin.future.java.headfirst.builder;

import com.f_lin.future.java.headfirst.builder.impl.AppleManorBuilder;
import com.f_lin.future.java.headfirst.builder.impl.OrangeManorBuilder;

/**
 * 建造者模式关注的是零件类型和装配工艺（顺序）
 * <p>
 * 建造者模式最主要的功能是基本方法的
 * 调用顺序安排，也就是这些基本方法已经实现了，通俗地说就是零件的装配，顺序不同产生
 * 的对象也不同；而工厂方法则重点是创建，创建零件是它的主要职责，组装顺序则不是它关
 * 心的。
 * <p>
 * BuilderDirector 封装所有构建场景。负责调用构建器
 * ==>
 * {@link IManorBuilder} 负责 构建特定的 Manor
 * ==>
 * {@link Manor} 则负责 执行不同方法
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:57
 **/
public class BuilderDirector {

    private AppleManorBuilder appleManorBuilder = new AppleManorBuilder();
    private OrangeManorBuilder orangeManorBuilder = new OrangeManorBuilder();

    public Manor getAppleManorOfLaLa() {
        return appleManorBuilder.builderManor("la_la");
    }

    public Manor getOrangeManorOfDADA() {
        return orangeManorBuilder.builderManor("da_da");
    }


}
