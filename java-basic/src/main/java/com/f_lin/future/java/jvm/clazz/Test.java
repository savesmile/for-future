package com.f_lin.future.java.jvm.clazz;

import java.util.ArrayList;
import java.util.List;

/**
 * @author F_lin fengjunlin@sobey.com
 * @version 1.0
 * @since 2022/3/18
 */
public class Test {

    private int x;

    public void inc() {
        ++x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void rawType() {
        List<String> sList = new ArrayList<>();
        List<Integer> iList = new ArrayList<>();

        sList.add("ss");
        sList.add("dd");
        iList.add(1);
        iList.add(2);

        for (String s : sList) {
            System.out.println(s);
        }

        for (Integer i : iList) {
            System.out.println(i);
        }

    }
}
