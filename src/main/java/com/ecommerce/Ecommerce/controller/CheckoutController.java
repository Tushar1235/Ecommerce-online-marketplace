package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Dto.CheckoutDto;
import com.ecommerce.Ecommerce.service.CheckoutService;
import com.ecommerce.Ecommerce.view.StripeResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private CheckoutService checkoutService;
    @Autowired
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutDto> checkoutItemDtoList) throws StripeException{
        // create the stripe session
        Session session = checkoutService.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        // send the stripe session id in response
        return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
    }
}
