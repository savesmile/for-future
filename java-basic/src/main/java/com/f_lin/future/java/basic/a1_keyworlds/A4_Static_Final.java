package com.f_lin.future.java.basic.a1_keyworlds;

/**
 * static+final
 * 静态常量，编译期常量，编译时就确定值。（Java代码执行顺序，先编译为class文件，在用虚拟机加载class文件执行）
 * 放于方法区中的静态常量池。
 * 在编译阶段存入调用类的常量池中
 * 如果调用此常量的类不是定义常量的类，那么不会初始化定义常量的类，因为在编译阶段通过常量传播优化，已经将常量存到调用类的常量池中了
 * <p>
 * final
 * 常量，类加载时确定或者更靠后。
 * 当用final作用于类的成员变量时，成员变量（注意是类的成员变量，局部变量只需要保证在使用之前被初始化赋值即可）必须在定义时或者构造器中进行初始化赋值
 * 对于一个final变量，如果是基本数据类型的变量，则其数值一旦在初始化之后便不能更改；
 * 如果是引用类型的变量，则在对其初始化之后便不能再让其指向另一个对象。但是它指向的对象的内容是可变的
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/12 16:02
 **/
public class A4_Static_Final {

    public static void main(String[] args) {

        //常量传播优化，已经将常量存到当前类的常量池中
        System.out.println(A1_Static.STATIC_FINAL_TEST);  //static ==> 不会触发A1_Static的初始化
    }
}
