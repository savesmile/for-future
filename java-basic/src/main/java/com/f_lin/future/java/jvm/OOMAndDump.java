package com.f_lin.future.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/26 15:17
 **/
public class OOMAndDump {

    public OOMAndDump() {
    }

    public static void main(String[] args) {
        List<OOMAndDump> test = new ArrayList<>();
        for (; ; ) {
            test.add(new OOMAndDump());
        }
    }
}
