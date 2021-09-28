package com.f_lin.future.java.basic;

import org.junit.Test;

/**
 * 基础类型的一些知识点
 * 8种基础类型,编译后是放在常量池中,之后转移到运行时常量池中的.所以必须赋初始值
 *
 * 1byte = 8bit (-2^7~2^7-1),第一位是符号位。
 * 所以只能表示 0111 1111(2) = 127(10)， 128(10) = 1000 0000(2)[用带符号位的2进制表示，为 -128]。
 *
 * byte     short   int     long    float   double  char    boolean
 * 1byte    2byte   4byte   8byte   4byte   8byte   2byte   4byte(编译后在JVM中会通过int类型来表示，而boolean[]以byte[]表示，一个数据1byte)
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/11 11:48
 **/
public class A3_BasicType {

    @Test
    public void testOrderConvert() {
        //表数范围小的向范围大的转换。可以直接转换。
        byte bt1 = 20;
        short st1 = bt1;
        int it1 = bt1;
        long lg1 = bt1;

        float ft1 = 3.5F;
        double db1 = ft1;

        //表数范围大的向范围小的转换。编译错误...需要强转，有些会导致损失精度，有些会导致数据溢出。
        long lg2 = 2333112334122L;
        int it2 = (int) lg2;    //945092394     数据溢出
        byte bt2 = (byte) lg2;  //42            数据溢出
        double db2 = 3.6688778899D;
        float ft2 = (float) db2; //3.6688778    精度丢失
        int it3 = 124;
        byte bt3 = (byte) it3;

        //自动类型提升。
        int int4 = 888;
        byte bt4 = 123;
        //bt4 自动将类型提升到 int 进行计算，所以没问题.
        int it5 = int4 + bt4;   //1011

        //与字符串拼接...遇`+`会转换为`字符串`.
        //原理: `+`号,编译后其实是以 StringBuilder.init<> -> append() -> toString() 实现的
        int it6 = 1000;
        int it7 = 1111;
        String str1 = it7 + it6 + "SS"; //2111SS
        String str2 = it6 + "SS" + it7; //1000SS1111
        String str3 = "SS" + it6 +  it7; //SS10001111
    }


}
