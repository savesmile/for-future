package com.f_lin.future.spring.cloud.feign;

import feign.Feign;
import feign.codec.StringDecoder;

/**
 * @author F_Lin
 * @date 2019/7/23 17:46
 * @description: TODO
 **/

public class FeignAnalyze {

    public static void main(String... args) {

        FeignTests feignTests = Feign.builder()
                .decoder(new StringDecoder())
                .target(FeignTests.class, "https://api.github.com");

        String feign = feignTests.helloFeign("OpenFeign", "feign");

        System.out.println("测试接口返回值为：" + feign);
    }


}

