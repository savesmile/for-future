package com.f_lin.future.java.basic.a1_common_po;

/**
 * javap -c -s -v -l
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/30 9:44
 **/
public class Person{
    private String name;
    private int sex;
    private Integer height;
    private Integer weight;

    public Person(String name, int sex, Integer height, Integer weight) {
        this.name = name;
        this.sex = sex;
        this.height = height;
        this.weight = weight;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void sayHello(){
        System.out.println("HELLO! WORL:D");
    }
}
