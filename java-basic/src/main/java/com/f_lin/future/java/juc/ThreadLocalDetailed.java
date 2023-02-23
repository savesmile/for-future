package com.f_lin.future.java.juc;

import io.netty.util.concurrent.FastThreadLocal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2021/9/28
 */
public class ThreadLocalDetailed {

    ThreadLocal<DateFormat> dfThreadLocal = ThreadLocal.withInitial(()-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    FastThreadLocal<DateFormat> fThreadLocal = new FastThreadLocal<>();

}
