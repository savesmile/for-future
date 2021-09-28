package com.f_lin.future.java.basic.a1_keyworlds;

/**
 * 基础关键字 static
 * 1.修饰常量。
 * 2.修饰方法。
 * 3.修饰代码块。
 * 4.修饰类。静态内部类。
 *
 * >
 * 静态引入。 import static xxxx.xxx
 * 加载顺序。
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/11 11:45
 **/
public class A1_Static {

    /**
     * static
     */
    public static String key_static = "static";

    /**
     * 静态常量
     */
    public static final String STATIC_FINAL_TEST = "static";

    /**
     * 静态块里面的代码只执行一次，且只在初始化类的时候执行
     *
     * 类初始化。参考类加载过程：加载-(验证-准备-解析)/连接-初始化
     *
     * 类加载时机：
     * 1.当遇到 new 、 getstatic、putstatic或invokestatic 这4条直接码指令时，即：new 一个类，读取一个静态字段，修改一个静态字段(未被 final 修饰)、或调用一个类的静态方法时。
     * 2.使用 java.lang.reflect 包的方法对类进行反射调用时 ，如果类没初始化，需要触发其初始化。
     * 3.初始化一个类，如果其父类还未初始化，则先触发该父类的初始化。
     * 4.当虚拟机启动时，用户需要定义一个要执行的主类 (包含 main 方法的那个类)，虚拟机会先初始化这个类。
     * 5.当使用 JDK1.7 的动态语言时，如果一个 MethodHandle 实例的最后解析结构为 REF_getStatic、REF_putStatic、REF_invokeStatic、的方法句柄，并且这个句柄没有初始化，则需要先触发其初始化。
     * 第五点理解。参考有道笔记
     */
    static {
        key_static = "hello";
        /**
         * 对于顺序在静态代码块后边的静态常量。可以赋值，但无法引用。
         * 理由：
         * 可以赋值：类编译的时候，优先将类静态常量编译到常量池中，再编译的静态方法。所以顺序上，常量句柄在前，方法引用在后。
         * 无法引用：这里可以先行理解类加载过程中->准备阶段 + 解析阶段。
         * 准备阶段在正真进行类初始化之前就已经将类相关常量进行了零值初始，而且在解析阶段将字面引用替换为了直接引用，但是没有进行正真的初始化过程，
         * 如果此常量是一个对象，则零值初始为null,进行使用将有空指针等错误，为杜绝此类操作。
         */
        key_static_after = "after";
        System.out.println("enter A1_Static.static block!");

        /**
         * 编译错误
         * <p>
         * 静态资源的加载顺序是严格按照静态资源的定义顺序来加载的
         * <clinit>()方法是由编译器自动收集类中所有类变量的赋值动作和静态语句块（static{}块）中的语句合并产生的，编译器收集的顺序是由语句在源文件中出现的顺序所决定的
         *          --《深入理解Java虚拟机：JVM高级特性与最佳实践》
         */
        //System.out.println(key_static_after);
    }

    private static String key_static_after = "static";

    public static void main(String[] args) {
        A1_Static a1 = new A1_Static();
        System.out.println(key_static);
        System.out.println(key_static_after);
    }

    /**
     * 1、静态内部类中可以有静态方法，也可以有非静态方法
     * 2、静态内部类只能访问其外部类的静态成员与静态方法
     * 3、和普通的类一样，要访问静态内部类的静态方法，可以直接"."出来不需要一个类实例；要访问静态内部类的非静态方法，必须拿到一个静态内部类的实例对象
     * 4、注意一下实例化成员内部类和实例化静态内部类这两种不同的内部类时写法上的差别
     * （1）成员内部类：外部类.内部类 XXX = 外部类.new 内部类();
     * （2）静态内部类：外部类.内部类 XXX = new 外部类.内部类();
     */
    public static class StaticInnerClassTest{
        private String key;
        private String val;

        public StaticInnerClassTest(){}

        public StaticInnerClassTest(String key, String val) {
            this.key = key;
            this.val = val;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVal() {
            return val;
        }

        public void setVal(String val) {
            this.val = val;
        }
    }
}
