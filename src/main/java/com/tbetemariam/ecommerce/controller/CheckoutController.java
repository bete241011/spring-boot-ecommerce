package com.tbetemariam.ecommerce.controller;

import com.tbetemariam.ecommerce.dto.Purchase;
import com.tbetemariam.ecommerce.dto.PurchaseResponse;
import com.tbetemariam.ecommerce.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;

    @Autowired
     public CheckoutController(CheckoutService checkoutService) {
         this.checkoutService = checkoutService;
     }

     @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);

        return  purchaseResponse;
     }
}
