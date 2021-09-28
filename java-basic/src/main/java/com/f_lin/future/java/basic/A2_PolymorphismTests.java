package com.f_lin.future.java.basic;

/**
 *
 * 重写/覆盖的规则
 * 1. 访问修饰符的权限不能降低，如父类中public的方法，重写的时候也必须是public的；
 * 2. 返回值类型不能变；
 * 3. 方法名相同，参数类型，个数，顺序一致（不然就是重载了）;
 * 4. 父类的private方法，子类写了相同的方法也不能算作重写
 *
 * 多态:
 * 1. 编译期多态 ===> 重载  (静态)
 * 2. 运行时多态 ===> 重写  (动态)
 *
 * @author F_lin
 * @since 2019/4/15
 **/
public class A2_PolymorphismTests {

    public static void main(String[] args) {
        A a1 = new A();
        //多态。。运行期才知道状态
        A a2 = new B();
        B b = new B();
        C c = new C();
        D d = new D();

        /**
         *   继承链
         *   A <- B <- C
         *        ^-<- D
         */
        System.out.println("1--" + a1.show(b)); //A and A
        System.out.println("2--" + a1.show(c)); //A and A
        System.out.println("3--" + a1.show(d)); //A and D
        //B#(B obj) 重载 A#show(D obj) ==> 扩大了参数范围==> 不是覆盖 a2 #show(B b) #show(A a) #show(D d)
        //由于a2 引用类为 A 所以。实际调用时，是执行的A中的方法，除非A中的方法被子类重写/覆盖。
        //a2 实例为 B.其中 B#(A a) 覆盖了父类A 的 A#(A a) 方法。所以实际执行的是实例 B类的B#(A a)方法
        System.out.println("4--" + a2.show(b)); //B and A
        System.out.println("5--" + a2.show(c)); //B and A
        System.out.println("6--" + a2.show(d)); //A and D
        //B的方法 B#show(B b) B#show(A a) A#show(D d)
        System.out.println("7--" + b.show(b));  //B and B
        System.out.println("8--" + b.show(c));  //B and B
        System.out.println("9--" + b.show(d));  //A and D
    }


    public static class A {
        public String show(D obj) {
            return ("A and D");
        }

        public String show(A obj) {
            return ("A and A");
        }

    }

    public static class B extends A {
        public String show(B obj) {
            return ("B and B");
        }

        public String show(A obj) {
            return ("B and A");
        }
    }

    public static class C extends B {

    }

    public static class D extends B {

    }

}
