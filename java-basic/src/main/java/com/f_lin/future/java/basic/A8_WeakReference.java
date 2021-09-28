package com.f_lin.future.java.basic;

import java.lang.ref.WeakReference;

/**
 * 被弱引用关联的对象只能生存到下次GC前,下次GC必定回收此对象
 * SoftReference，下次GC时，若内存不会溢出，则不会回收其关联对象，反之，则会回收其关联对象
 *      适合缓存数据。。不用的时候不回收，系统撑不住了就直接回收。
 * Reference中 有一个变量-ReferenceQueue queue，，表示在WeakReference指向的对象被回收之后，可以利用ReferenceQueue来保存被回收的对象
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/19 18:00
 **/
public class A8_WeakReference {
    /**
     * ......
     * WeakReferenceCar's Car is alive for 84872, loop - com.f_lin.future.java.basic.WeakReferenceCar@4eec7777
     * WeakReferenceCar's Car is alive for 84873, loop - com.f_lin.future.java.basic.WeakReferenceCar@4eec7777
     * WeakReferenceCar's Car is alive for 84874, loop - com.f_lin.future.java.basic.WeakReferenceCar@4eec7777
     * WeakReferenceCar's Car is alive for 84875, loop - com.f_lin.future.java.basic.WeakReferenceCar@4eec7777
     * ====================执行GC===========================
     * [GC (Allocation Failure) [PSYoungGen: 66104K->904K(75776K)] 66112K->920K(249344K), 0.0009933 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * WeakReferenceCar's Car is alive for 84876, loop - com.f_lin.future.java.basic.WeakReferenceCar@4eec7777
     * ====================car被回收，而不是WeakReferenceCar被回收========================
     * WeakReferenceCar's Car has bean collected
     * Heap
     * PSYoungGen      total 75776K, used 4134K [0x000000076b780000, 0x0000000770c00000, 0x00000007c0000000)
     * eden space 65024K, 4% used [0x000000076b780000,0x000000076baa7940,0x000000076f700000)
     * from space 10752K, 8% used [0x0000000770180000,0x0000000770262020,0x0000000770c00000)
     * to   space 10752K, 0% used [0x000000076f700000,0x000000076f700000,0x0000000770180000)
     * ParOldGen       total 173568K, used 16K [0x00000006c2600000, 0x00000006ccf80000, 0x000000076b780000)
     * object space 173568K, 0% used [0x00000006c2600000,0x00000006c2604000,0x00000006ccf80000)
     * Metaspace       used 3296K, capacity 4500K, committed 4864K, reserved 1056768K
     * class space    used 351K, capacity 388K, committed 512K, reserved 1048576K
     */
    public static void main(String[] args) {
        Car car = new Car(2000.0, "red");
        WeakReferenceCar wrc = new WeakReferenceCar(car);
        int i = 0;
        while (true) {
            if (wrc.get() != null) {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            } else {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }
    }
}


class Car {
    private double price;
    private String color;

    public Car(double price, String color) {
        this.price = price;
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String toString() {
        return "This car is a " + this.color + " car, costs $" + price;
    }
}

class WeakReferenceCar extends WeakReference<Car> {
    public WeakReferenceCar(Car car) {
        super(car);
    }
}