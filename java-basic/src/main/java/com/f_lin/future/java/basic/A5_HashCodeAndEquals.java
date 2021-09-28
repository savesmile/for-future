package com.f_lin.future.java.basic;

import lombok.Getter;
import lombok.Setter;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 重写equals()尽量重写hashCode()
 * 在HashMap中,,通过hashCode()找到hash表中的位置,通过equals判断链表中的对象是否相等.
 * 需要理解HashMap原理....
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/15 10:07
 **/
public class A5_HashCodeAndEquals {


    @Test
    public void test() {
        Set<TestEntity> setTest = new HashSet<>();
        setTest.add(new TestEntity(1, "s", false));
        setTest.add(new TestEntity(1, "s", false));
        setTest.add(new TestEntity(1, "s", false));
        setTest.add(new TestEntity(1, "s", false));
        setTest.add(new TestEntity(1, "s", false));
        //按理说..5个 new A5_HashCodeAndEquals() 是相等的.所以set中应该只有一个值
        System.out.println("without rewrite hashCode(), size: " + setTest.size());   //5

        Set<TestEntity> hashSetTest = new HashSet<>();
        hashSetTest.add(new TestEntity(1, "s", false) {
            //可直接在类中重写,这里突出一下对比,就用内部类来进行重写
            @Override
            public int hashCode() {
                return this.myHashCode();
            }
        });
        hashSetTest.add(new TestEntity(1, "s", false) {
            //可直接在类中重写,这里突出一下对比,就用内部类来进行重写
            @Override
            public int hashCode() {
                return this.myHashCode();
            }
        });
        System.out.println("rewrite hashCode(), size: " + hashSetTest.size());   //1

    }

}

@Setter
@Getter
class TestEntity {

    private Integer intVal;

    private String strVal;

    private Boolean boolVal;

    public TestEntity(Integer intVal, String strVal, Boolean boolVal) {
        this.intVal = intVal;
        this.strVal = strVal;
        this.boolVal = boolVal;
    }

    public TestEntity() {
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TestEntity) {
            TestEntity other = (TestEntity) obj;
            return other.getIntVal().equals(this.intVal)
                    && other.getStrVal().equals(this.strVal)
                    && other.getBoolVal() == this.boolVal;

        }
        return false;
    }

    public int myHashCode() {
        return intVal.hashCode() + strVal.hashCode() + boolVal.hashCode();
    }
}
