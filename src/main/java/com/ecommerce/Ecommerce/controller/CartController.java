package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Dto.CartDto;
import com.ecommerce.Ecommerce.model.AuthenticationToken;
import com.ecommerce.Ecommerce.repository.AuthenticationTokenRepo;
import com.ecommerce.Ecommerce.service.CartService;
import com.ecommerce.Ecommerce.view.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    @Autowired
    AuthenticationTokenRepo tokenRepo;
    @PostMapping("/addToCart")
    public ResponseEntity<ApiResponse> addToCart(CartDto cartDto, @RequestParam String token){
        try{
            cartService.authenticateToken(token);
            AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
            cartDto.setId(authenticationToken.getUser().getId());
            cartService.addToCart(cartDto,authenticationToken);
        } catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ApiResponse(true,"Added to Cart."), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CartDto>> getAllCartItem(@RequestParam String token){
            cartService.authenticateToken(token);
            AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
            List<CartDto> cartDtos = cartService.getAllCartItem(authenticationToken);
        return  new ResponseEntity<>(cartDtos,HttpStatus.OK);
    }
}
