package com.f_lin.future.java.headfirst.factory.fruitimpl;

import com.f_lin.future.java.headfirst.factory.Fruit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/5 15:50
 **/
@Setter
@Getter
@Accessors(chain = true)
@NoArgsConstructor
public class Apple implements Fruit {
    private String name;
    private String color;

    @Override
    public String name() {
        return name == null ? "apple" : name;
    }

    @Override
    public String color() {
        return color == null ? "red" : color;
    }
}
