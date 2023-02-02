package com.f_lin.future.java.headfirst.factory.delay;

import com.f_lin.future.java.headfirst.factory.Fruit;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 延迟初始化
 * <p>
 * 一个对象被消费完毕后，并不立刻释放，工厂类
 * 保持其初始状态，等待再次被使用。
 * <p>
 * 限制某一个产品类的最大实例化数量
 * <p>
 * 例如JDBC连接数据库，都会
 * 要求设置一个MaxConnections最大连接数量，该数量就是内存中最大实例化的数量
 * <p>
 * 延迟加载还可以用在对象初始化比较复杂的情况下，例如硬件访问，涉及多方面的交
 * 互，则可以通过延迟加载降低对象的产生和销毁带来的复杂性
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 10:11
 **/
public class DelayInitFruitManor {

    private static final Map<String, Fruit> CACHE_FRUIT = new HashMap<>();

    public static <T extends Fruit> T cultivateFruit(Class<T> tClass) {
        String beanName = tClass.getSimpleName();
        if (CACHE_FRUIT.containsKey(beanName)) {
            return (T) CACHE_FRUIT.get(beanName);
        } else {
            Fruit fruit = null;
            try {
                fruit = tClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            CACHE_FRUIT.put(tClass.getSimpleName(), fruit);

            return (T) fruit;
        }
    }
}
