package com.f_lin.future.java.headfirst.memento;

import com.f_lin.future.java.headfirst.memento.impl.MemManager;
import com.f_lin.future.java.headfirst.memento.impl.Originator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/12 15:48
 **/
public class MainTests {

    public static void main(String[] args) {
        //定义出发起人
        Originator originator = new Originator();
        //定义出备忘录管理员
        MemManager caretaker = new MemManager();
        //创建一个备忘录
        caretaker.setMemento(originator.createMemento());
        //恢复一个备忘录
        originator.restoreMemento(caretaker.getMemento());


        FieldsTest fieldsTest = new FieldsTest("tom", "JT", false, 24);

        Map<String, Object> stringObjectMap = beanUtils(fieldsTest);

        stringObjectMap.forEach((k, v) -> System.out.println("k===> [" + k + "] ,v========> [" + v + "]"));

    }

    /**
     * 读取字段名 + 字段值的方法
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> beanUtils(Object obj) {
        Map<String, Object> result = new HashMap<>();

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
                String fieldName = desc.getName();
                Method readMethod = desc.getReadMethod();
                Object invoke = readMethod.invoke(obj);
                result.put(fieldName, invoke);
            }

        } catch (IntrospectionException | IllegalAccessException | InvocationTargetException ignore) {
        }
        return result;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldsTest {
        private String name;
        private String value;
        private boolean sex;
        private Integer age;
    }
}
