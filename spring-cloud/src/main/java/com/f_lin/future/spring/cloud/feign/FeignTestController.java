package com.f_lin.future.spring.cloud.feign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author F_Lin
 * @date 2019/7/23 18:09
 * @description: TODO
 **/
@RestController
@RequestMapping("/test")
public class FeignTestController {

    @GetMapping("/{module}/{type}/one")
    public String test(@PathVariable("module") String module, @PathVariable("type") String type) {
        return module + "_" + type;
    }
}
