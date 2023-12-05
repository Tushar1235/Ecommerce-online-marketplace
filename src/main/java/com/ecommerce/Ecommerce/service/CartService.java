package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.Dto.CartDto;
import com.ecommerce.Ecommerce.Dto.ProductDto;
import com.ecommerce.Ecommerce.customeException.CustomException;
import com.ecommerce.Ecommerce.model.*;
import com.ecommerce.Ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    AuthenticationTokenRepo tokenRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WIshlistRepository wishlistRepository;
    @Autowired
    CartRepository cartRepository;
    public void addToCart(CartDto cartDto, AuthenticationToken authenticationToken) {
        User user = userRepository.findById(authenticationToken.getUser().getId()).orElseThrow(()->new CustomException("User not found"));
        Product product = productRepository.findById(cartDto.getProduct_id()).orElseThrow(()-> new CustomException("Product not found"));
        Cart cart = new Cart(product,user,cartDto.getQuantity());
        cartRepository.save(cart);

    }
    public void authenticateToken(String token) {
        if(Objects.isNull(tokenRepo.findByToken(token))){
            throw  new CustomException("Please Login to continue");
        }
    }

    public List<CartDto> getAllCartItem(AuthenticationToken authenticationToken) {
        User user = userRepository.findById(authenticationToken.getUser().getId()).orElseThrow(()->new CustomException("User not found"));
        List<Cart> carts = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartDto> cartDtos = new ArrayList<>();

        for(Cart cart : carts){
            CartDto cartDto = new CartDto();
            cartDto.setProduct_id(cart.getProduct().getId());
            cartDto.setUser_id(cart.getUser().getId());
            cartDto.setQuantity(cart.getQuantity());
            double total = (cartDto.getQuantity()) * productRepository.findById(cart.getProduct().getId()).orElseThrow(()-> new CustomException("Product not found")).getPrice();
            cartDto.setTotal(total);
            cartDtos.add(cartDto);
        }
        return  cartDtos;
    }
}
