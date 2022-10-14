package com.fzz.springcloud.Controller;

import com.fzz.springcloud.entity.CommonResult;
import com.fzz.springcloud.entity.Payment;
import com.fzz.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/payment/create")
    public CommonResult<Integer> create(@RequestBody Payment payment){
        int i = paymentService.create(payment);
        if(i>0){
            return new CommonResult<>(200,"添加数据成功",i);
        }else{
            return new CommonResult<>(404,"添加数据失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if(payment!=null){
            return new CommonResult<>(200,"查询成功,port:"+8001,payment);
        }else{
            return new CommonResult<>(404,"查不到id为"+id+"的数据",null);
        }
    }

    @GetMapping("/payment/ribbon")
    public String getServerPort(){
        return serverPort;
    }

    @GetMapping("/payment/feign/timeout")
    public String testTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
