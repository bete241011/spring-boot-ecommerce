package com.tbetemariam.ecommerce.service;

import com.tbetemariam.ecommerce.dto.Purchase;
import com.tbetemariam.ecommerce.dto.PurchaseResponse;
import com.tbetemariam.ecommerce.models.Customer;
import com.tbetemariam.ecommerce.models.Order;
import com.tbetemariam.ecommerce.models.OrderItem;
import com.tbetemariam.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
//        retrieve the order info from dto
        Order order = purchase.getOrder();

//        generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

//        populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::add);

//        populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

//        populate customer with order
        Customer customer = purchase.getCustomer();

//        check if this is an existing customer
        String theEmail = customer.getEmail();
        Customer customerFromDB = customerRepository.findByEmail(theEmail);
        if (customerFromDB != null) {
//            we found them ... let's assign them accordingly
            customer = customerFromDB;
        }

        customer.add(order);

//        save to the database
        customerRepository.save(customer);

//        return a response
        return  new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
//        generate a random UUID number(UUID version - 4)
        return UUID.randomUUID().toString();
    }
}
