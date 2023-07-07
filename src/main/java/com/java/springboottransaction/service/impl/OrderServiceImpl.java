package com.java.springboottransaction.service.impl;

import com.java.springboottransaction.dto.OrderRequest;
import com.java.springboottransaction.dto.OrderResponse;
import com.java.springboottransaction.entity.Order;
import com.java.springboottransaction.entity.Payment;
import com.java.springboottransaction.exception.PaymentException;
import com.java.springboottransaction.repository.OrderRepository;
import com.java.springboottransaction.repository.PaymentRepository;
import com.java.springboottransaction.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private PaymentRepository paymentRepository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRepository paymentRepository) {
        this.orderRepository = orderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order= orderRequest.getOrder();
        order.setStatus("IN PROGRESS");
        order.setOrderTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);
        Payment payment = orderRequest.getPayment();
        if (!payment.getType().equals("DEBIT")){
            throw new PaymentException("Payment card type is  not supported");

        }
        payment.setOrderId(order.getId());
        paymentRepository.save(payment);
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("SUCCESS");


        return orderResponse;
    }
}

