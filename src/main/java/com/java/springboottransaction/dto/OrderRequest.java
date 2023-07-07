package com.java.springboottransaction.dto;

import com.java.springboottransaction.entity.Order;
import com.java.springboottransaction.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderRequest {
    private Order order;
    private Payment payment;
}
