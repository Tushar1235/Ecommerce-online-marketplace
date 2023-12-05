package com.ecommerce.Ecommerce.controller;

import com.ecommerce.Ecommerce.Dto.ProductDto;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.service.WishListService;
import com.ecommerce.Ecommerce.view.ApiResponse;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;
    //add
    @PostMapping("/add/{productId}")
    public ResponseEntity<ApiResponse> addToWishList(@PathVariable Integer productId, @RequestParam String token){
        try{
            wishListService.add(productId,token);
            return new ResponseEntity<>(new ApiResponse(true,"Product Added to Wishlist"), HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }
    //get wishlist item

    @GetMapping
    public List<ProductDto> getWishlistItem(@RequestParam String token){
        return wishListService.getallWishlistItem(token);
    }
}
