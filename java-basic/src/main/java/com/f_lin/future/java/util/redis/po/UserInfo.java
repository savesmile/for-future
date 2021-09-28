package com.f_lin.future.java.util.redis.po;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author F_Lin fengjunlin@modnim.com
 * @date 2019/11/22 11:32
 **/
@Data
@Accessors(chain = true)
public class UserInfo {
    private String userId;
    private String name;
    private Integer age;
    private Boolean sex;
    private Double height;
}
