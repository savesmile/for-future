package com.f_lin.future.java.basic.a1_keyworlds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * final作用
 * 1. 不可继承
 * 2. 不可复写
 * 3. 不可修改。（指引用不可修改，而引用的内容可以修改！）
 * 4. 被final修饰的方法，JVM会尝试为之寻求内联，这对于提升Java的效率是非常重要的。
 * 因此，假如能确定方法不会被继承，那么尽量将方法定义为final的
 * 5. 被final修饰的常量，在编译阶段会存入调用类的常量池中
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/11 15:31
 **/
public class A2_Final {

    private final String key_final = "final";
    private final List<String> key_finals = new ArrayList<>(Arrays.asList("final", "key2", "key3"));

    public static void main(String[] args) {
        A2_Final a2_final = new A2_Final();
        a2_final.key_finals.add("key4");

        //编译错误
        //修改引用。
        //a2_final.key_finals = Collections.EMPTY_LIST;
    }


    private void finalTest(final List<String> src) {
        src.add("hello");
        //编译错误
        //修改引用。
        //src = Collections.emptyList();
    }
}
