package com.f_lin.future.java.basic;

/**
 * 为何要定制自己的ClassLoader
 * 1.
 *
 *
 * 定制自己的 ClassLoader
 * 1. 遵循双亲委派模型：直接重写findClass()即可。
 * 2. 打破双亲委派模型：直接重写 loadClass()即可。
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/15 16:59
 **/
public class A6_ClassLoader {


    /**
     * 遵循双亲委派
     */
    public static class CustomizeClassLoader extends ClassLoader {

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            return super.findClass(name);
        }
    }

    /**
     * 打破双亲委派
     */
    public static class CustomizeBreakClassLoader extends ClassLoader {

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            return super.loadClass(name, resolve);
        }
    }
}

