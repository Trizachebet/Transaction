package com.java.springboottransaction.service;

import com.java.springboottransaction.dto.OrderRequest;
import com.java.springboottransaction.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
