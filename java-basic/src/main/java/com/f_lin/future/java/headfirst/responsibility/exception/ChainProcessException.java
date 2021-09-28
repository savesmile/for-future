package com.f_lin.future.java.headfirst.responsibility.exception;

/**
 * 异常 TODO 待完善异常处理机制
 * @author F_lin
 * @since 2019/4/12
 **/
public class ChainProcessException extends RuntimeException {
    private String key;
    private Integer errorCode;
}
