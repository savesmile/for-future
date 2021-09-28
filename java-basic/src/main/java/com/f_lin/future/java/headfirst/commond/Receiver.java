package com.f_lin.future.java.headfirst.commond;

/**
 * 命令接受器
 * <p>
 *
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/9/6 14:57
 **/
public interface Receiver {

    /**
     * 执行命令
     *
     * @param commandStr 命令字符串
     */
    default void action(String commandStr) {
        analyze(commandStr).execute();
    }

    /**
     * 解析命令
     *
     * @param str 命令参数
     */
    Command analyze(String str);

}
