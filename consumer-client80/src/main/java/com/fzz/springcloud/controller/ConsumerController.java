package com.fzz.springcloud.controller;

import com.fzz.springcloud.entity.CommonResult;
import com.fzz.springcloud.entity.Payment;
import com.fzz.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class ConsumerController {

//    private static final String SERVER_URL="http://localhost:8001/";
    private static final String SERVER_URL="http://PROVIDER-PAYMENT";
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancer loadBalancer;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Integer> create(Payment payment){
        return restTemplate.postForObject(SERVER_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable Long id){
        return restTemplate.getForObject(SERVER_URL+"/payment/get/"+id,CommonResult.class);
    }

    /**
     * 测试手写客户端负载均衡的轮询算法
     *
     */
    @GetMapping("/consumer/payment/ribbon")
    public String getServerPort(){
        List<ServiceInstance> instances = discoveryClient.getInstances("PROVIDER-PAYMENT");
        if(instances==null||instances.size()<0){
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.serviceInstance(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/ribbon",String.class);
    }

}
