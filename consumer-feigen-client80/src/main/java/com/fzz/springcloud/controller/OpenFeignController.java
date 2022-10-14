package com.fzz.springcloud.controller;

import com.fzz.springcloud.entity.CommonResult;
import com.fzz.springcloud.entity.Payment;
import com.fzz.springcloud.service.OpenFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenFeignController {
    @Autowired
    private OpenFeignService openFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return openFeignService.getPaymentById(id);
    }

    @GetMapping("/consumer/payment/feign/timeout")
    public String testTimeout(){
        return openFeignService.testTimeout();
    }

}
