package com.fzz.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer{

    private AtomicInteger atomicInteger=new AtomicInteger(0);

    private static final Integer MAX_NUMBER=Integer.MAX_VALUE;

    public final int getNext(){
        int current;
        int next;
        do{
            current=this.atomicInteger.get();
            next=current>=MAX_NUMBER?0:current+1;
        }while(this.atomicInteger.compareAndSet(current,next));
        return next;
    }

    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> lists) {
        int index = getNext() % lists.size();
        return lists.get(index);
    }
}
