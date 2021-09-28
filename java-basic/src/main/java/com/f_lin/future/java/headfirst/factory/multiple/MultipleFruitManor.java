package com.f_lin.future.java.headfirst.factory.multiple;

import com.f_lin.future.java.headfirst.factory.Fruit;

/**
 * 多工厂模式
 * <p>
 * 当我们在做一个比较复杂的项目时，经常会遇到初始化一个对象很耗费精力的情况，所
 * 有的产品类都放到一个工厂方法中进行初始化会使代码结构不清晰。例如，一个产品类有5
 * 个具体实现，每个实现类的初始化（不仅仅是new，初始化包括new一个对象，并对对象设
 * 置一定的初始值）方法都不相同，如果写在一个工厂方法中，势必会导致该方法巨大无比
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 9:58
 **/
public interface MultipleFruitManor {

    Fruit cultivateFruit();

}
