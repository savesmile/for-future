package com.f_lin.future.spring.cloud.feign;

import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author F_Lin
 * @date 2019/7/23 17:47
 * @description: TODO
 **/
@FeignClient(name = "test", primary = false)
public interface FeignTests {

    @RequestLine("GET_/test/{module}/{type}/one")
    String helloFeign(@Param("module") String module, @Param("type") String type);

}
