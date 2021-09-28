package com.f_lin.future.spring.cloud.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author F_Lin
 * @date 2019/7/23 18:04
 * @description: TODO
 **/
@Configuration
public class DefaultFeignConfig {

    @Bean
    public Contract contract() {
        return new Contract.Default();
    }
}
