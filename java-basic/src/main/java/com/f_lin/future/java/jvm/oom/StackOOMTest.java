package com.f_lin.future.java.jvm.oom;

/**
 * -Xss180k
 *
 * 发送栈溢出Ex的条件
 * 1）如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。
 * 2）如果虚拟机的栈内存允许动态扩展，当扩展栈容量无法申请到足够的内存时，将抛出 OutOfMemoryError异常。
 *
 * 而HotSpot虚拟机 的选择是不支持扩展，
 * 所以除非在创建线程申请内存时就因无法获得足够内存而出现 OutOfMemoryError异常，
 * 否则在线程运行时是不会因为扩展而导致内存溢出的，只会因为栈容量无法容纳新的栈帧而导致StackOverflowError异常。
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2022/3/3
 */
public class StackOOMTest {


    static int i = 0;

    public static void main(String[] args) {
        test();
    }

    static void test() {
        System.out.println("deep: ["+(++i)+"]");
        int x1,x2,x3,x4;
        test();
        x1 = x2 = x3 = x4 = 1;
    }

}
