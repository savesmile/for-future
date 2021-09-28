package com.f_lin.future.java.basic;

import com.f_lin.future.java.basic.a1_keyworlds.A1_Static;
import org.junit.Test;

/**
 * String == String  StringBuilder & StringBuffer
 * <p>
 * https://www.cnblogs.com/syp172654682/p/8082625.html
 * <p>
 * **静态常量池：
 * 编译后.class文件中的 Constant pool，包括：
 * ***字面量
 * 字面量相当于Java语言层面常量的概念
 * 如文本字符串 //比如 String str = "abc" ,abc为字面量。可以笼统的理解为，引用指向的值。
 * 声明为final的常量值等  //由于final关键字特性，不可修改指向引用，所以可以确定当前final。
 * <p>
 * ***符号引用量，符号引用则属于编译原理方面的概念，包括了如下三种类型的常量：
 * 类和接口的全限定名
 * 字段名称和描述符
 * 方法名称和描述符
 * <p>
 * *运行期常量池：
 * 在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，就是指方法区中的运行时常量池。
 * 具备动态性
 * <p>
 * 存放对象：在编译器已经被确认，并且保存在已经编译后的.class文件中的一些数据。
 * 包括：
 * 1. 8种基础数据。
 * 2. String
 * 3. 数组常量值
 * 4. 文本形式的符号引用
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/12 9:39
 **/
public class A4_String {

    private final A1_Static finaleTest = new A1_Static();

    public A4_String() {
    }

    @Test
    public void testStr() {
        String str1 = "abc";
        String str2 = "abc";

        System.out.println(str1 == str2);  //true

        String str3 = new String("cde");
        String str4 = new String("cde");
        System.out.println(str3 == str4);  //false

    }

    /**
     * String 不推荐使用 + 进行字符串拼接。
     * 编译后的方法
     * public void testStrBuilder();
     * descriptor: ()V
     * flags: ACC_PUBLIC
     * Code:
     * stack=2, locals=2, args_size=1
     * 0: ldc           #2                  // String abc
     * 2: astore_1
     * ====================//第一次+=。。实际是调用 StringBuilder 经历 init()->append()->toString() 进行操作的。
     * 3: new           #8                  // class java/lang/StringBuilder
     * 6: dup
     * 7: invokespecial #9                  // Method java/lang/StringBuilder."<init>":()V
     * 10: aload_1
     * 11: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 14: ldc           #11                 // String def
     * 16: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 19: invokevirtual #12                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * 22: astore_1
     * ====================//第二次+=。。再次new 了一个StringBuilder 经历 init()->append()->toString()。
     * 23: new           #8                  // class java/lang/StringBuilder
     * 26: dup
     * 27: invokespecial #9                  // Method java/lang/StringBuilder."<init>":()V
     * 30: aload_1
     * 31: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 34: ldc           #13                 // String ghi
     * 36: invokevirtual #10                 // Method java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
     * 39: invokevirtual #12                 // Method java/lang/StringBuilder.toString:()Ljava/lang/String;
     * ...省略
     */
    @Test
    public void testStrBuilder() {
        String str1 = "abc";
        str1 += "def";
        str1 += "ghi";

        System.out.println(str1);
    }


    @Test
    public void testEqual() {
        //在常量池中 加入 "Hello",并在heap中创建一个String对象，其引用指向常量池"Hello"地址
        String s1 = "Hello";
        String s2 = "Hello";
        //编译期优化，由于"Hel"\"lo"都是已知数值，使用 `+` 连接。在编译时，直接会被优化成 "Hello"
        String s3 = "Hel" + "lo";
        //new String() 会在heap中创建一个对象，是一个未知变量，需要在运行时确认，
        //使用`+`连接的话，会使用StringBuilder.append()+toString，重新在heap中创建一个String对象，且指向的'hello'并不会加入到常量池中
        String s4 = "Hel" + new String("lo");
        //存在heap中
        String s5 = new String("Hello");
        //将heap中的值 推到常量池中，如果之前有这个字面量值(xx)的话，就直接将xx的地址赋值给引用`s6`.
        String s6 = s5.intern();
        String s7 = "H";
        String s8 = "ello";
        //s7 s8都是放在heap中的两个String实例，两者相加，是一个新的String,且并不指向常量池中的"hello"
        String s9 = s7 + s8;

        System.out.println(s1 == s2);  // true
        System.out.println(s1 == s3);  // true
        System.out.println(s1 == s4);  // false
        System.out.println(s1 == s9);  // false
        System.out.println(s4 == s5);  // false
        System.out.println(s1 == s6);  // true
    }
}
