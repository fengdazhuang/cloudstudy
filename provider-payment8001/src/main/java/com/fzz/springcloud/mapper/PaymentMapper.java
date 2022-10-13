package com.fzz.springcloud.mapper;

import com.fzz.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
