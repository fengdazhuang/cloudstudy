package com.fzz.springcloud.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/payment/zk")
    public String index(){
        return "zookeeper注册"+ UUID.randomUUID().toString();
    }
}
