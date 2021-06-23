package com.tbetemariam.ecommerce.dto;

import com.tbetemariam.ecommerce.models.Address;
import com.tbetemariam.ecommerce.models.Customer;
import com.tbetemariam.ecommerce.models.Order;
import com.tbetemariam.ecommerce.models.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
