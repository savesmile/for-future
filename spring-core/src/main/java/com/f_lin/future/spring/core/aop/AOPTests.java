package com.f_lin.future.spring.core.aop;

import com.f_lin.future.spring.core.aop.tests.Cat;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author F_lin
 * @since 2019/4/15
 * <p>
 * <p>
 * 常用的execution:
 * execution(<scope> <return-type> <fully-qualified-class-name>.*(parameters))
 * scope ：方法作用域，如public,private,protect
 * return-type：方法返回值类型
 * fully-qualified-class-name：方法所在类的完全限定名称
 * parameters 方法参数
 **/
public class AOPTests {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("core.xml");
        Cat cat = context.getBean(Cat.class);
        cat.say();
    }

}
