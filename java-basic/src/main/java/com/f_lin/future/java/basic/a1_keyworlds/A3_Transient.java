package com.f_lin.future.java.basic.a1_keyworlds;

import lombok.ToString;

import java.io.*;

/**
 * transient
 * 作用：不可序列化
 *
 * 序列化：将一个对象转换成一个二进制表示的字节数组，用于转移或者保存，对象信息，状态等
 * 反序列化：将字节数组转变为对象
 *
 * 代价很严重
 *
 * 1. 父类实现了 Serializable 接口，子类都可序列化
 * 2. 子类实现了 Serializable 接口而父类未实现的话，则父类字段丢失，子类正常序列化
 * 3. 如果序列化字段为对象的话，此对象也必须实现 Serializable 接口，否则报错
 * 4. 反序列化时，如果对象字段有增减，则相关字段丢失，不报错。
 * 5. 反序列化时，如果serialVersionUID 被修改，则反序列化失败。
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/10/11 15:44
 **/
@ToString
public class A3_Transient implements Serializable {

    /**
     * Java序列化机制是通过在运行时判断类的serialVersionUID来验证版本一致性的
     */
    private static final long serialVersionUID = -7694998261673876658L;

    private transient String key_transient = "transient";

    private String notTransient = "hello";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("D:\\data\\test.txt");

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));

        os.writeObject(new A3_Transient());


        ObjectInputStream oi = new ObjectInputStream(new FileInputStream(file));
        A3_Transient readObject = (A3_Transient) oi.readObject();

        System.out.println(readObject.toString());

        os.close();
        oi.close();
    }
}
