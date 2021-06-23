package com.tbetemariam.ecommerce.service;

import com.tbetemariam.ecommerce.dto.Purchase;
import com.tbetemariam.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
