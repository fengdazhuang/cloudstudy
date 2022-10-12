package com.fzz.springcloud.service;

import com.fzz.springcloud.entity.Payment;

public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(Long id);
}
