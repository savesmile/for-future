package com.f_lin.future.java.headfirst.factory;

import com.f_lin.future.java.headfirst.factory.fruitimpl.Apple;
import com.f_lin.future.java.headfirst.factory.fruitimpl.Banana;
import com.f_lin.future.java.headfirst.factory.fruitimpl.Peach;
import com.f_lin.future.java.headfirst.factory.methods.MethodFruitManor;
import com.f_lin.future.java.headfirst.factory.multiple.AppleFruitManor;
import com.f_lin.future.java.headfirst.factory.simple.SimpleFruitManor;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/5 15:58
 **/
public class mainTests {

    public static void main(String[] args) {


    }



    public static void multiTest() {
        AppleFruitManor appleFruitManor = new AppleFruitManor();
        Fruit app = appleFruitManor.cultivateFruit();
        System.out.println("水果名：" + app.name() + " ========= 颜色是：" + app.color());
        //....
    }


    public static void simpleTest() {
        Apple apple = SimpleFruitManor.cultivateFruit(Apple.class);
        System.out.println("水果名：" + apple.name() + " ========= 颜色是：" + apple.color());
        //.......
    }


    public static void methodTest() {
        MethodFruitManor simpleFruitManor = new MethodFruitManor();
        Fruit apple = simpleFruitManor.cultivateFruit(Apple.class);
        System.out.println("水果名：" + apple.name() + " ========= 颜色是：" + apple.color());

        Fruit banana = simpleFruitManor.cultivateFruit(Banana.class);
        System.out.println("水果名：" + banana.name() + " ========= 颜色是：" + banana.color());

        Fruit peach = simpleFruitManor.cultivateFruit(Peach.class);
        System.out.println("水果名：" + peach.name() + " ========= 颜色是：" + peach.color());
    }
}
