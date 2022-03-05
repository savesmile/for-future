package com.f_lin.future.java.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2022/3/3
 */
public class HeapOOMTest {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }

}
