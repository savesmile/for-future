package com.f_lin.future.java.basic;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Random;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/11/16
 */
public class MethodHandleTest {


    public void println(Object value) {
        System.out.println("value=" + value);
    }

    public static MethodHandle getMethodHandle(Object receiver) throws Throwable {
        //如果Lookup对象
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        //MethodType代表方法的类型(不包含方法名称)，其实MethodType是为了确定方法的描述符，例如此方法描述符为：(Ljava/lang/Object;)V
        MethodType methodType = MethodType.methodType(void.class, Object.class);
        //在接收者类中查找一个名为println，指定方法类型的虚方法
        MethodHandle virtual = lookup.findVirtual(receiver.getClass(), "println", methodType);
        return virtual.bindTo(receiver);
    }


    public static void main(String[] args) throws Throwable {
        Object receiver = new Random().nextInt(1000) % 2 == 0 ? System.out : new MethodHandleTest();
        // 无论receiver最终是什么类型，只要有println方法，方法就可以正常调用。
        getMethodHandle(receiver).invoke("Hello Dynamic Invoke");
    }

}
