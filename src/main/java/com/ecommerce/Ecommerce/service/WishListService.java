package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.Dto.ProductDto;
import com.ecommerce.Ecommerce.customeException.CustomException;
import com.ecommerce.Ecommerce.model.AuthenticationToken;
import com.ecommerce.Ecommerce.model.Product;
import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.model.Wishlist;
import com.ecommerce.Ecommerce.repository.AuthenticationTokenRepo;
import com.ecommerce.Ecommerce.repository.ProductRepository;
import com.ecommerce.Ecommerce.repository.UserRepository;
import com.ecommerce.Ecommerce.repository.WIshlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class WishListService {

    @Autowired
    AuthenticationTokenRepo tokenRepo;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    WIshlistRepository wishlistRepository;
    public void add(Integer productId, String token) {
            authenticateToken(token);
            AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
            User user = userRepository.findById(authenticationToken.getUser().getId()).orElseThrow(()->new CustomException("User not found"));
            Product product = productRepository.findById(productId).orElseThrow(()-> new CustomException("Product not found"));
            Wishlist wishlist = new Wishlist(user,product);
        wishlistRepository.save(wishlist);
    }

    private void authenticateToken(String token) {
        if(Objects.isNull(tokenRepo.findByToken(token))){
            throw  new CustomException("Please Login to continue");
        }
    }

    public List<ProductDto> getallWishlistItem(String token) {
            authenticateToken(token);
            AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
            User user = userRepository.findById(authenticationToken.getUser().getId()).orElseThrow(()->new CustomException("User not found"));
            List<Wishlist> wishlist = wishlistRepository.findAllByUserOrderByCreatedDateDesc(user);

            List<Product> products = new ArrayList<>();
            for(Wishlist wishlist1:wishlist){
                products.add(productRepository.findById(wishlist1.getProduct().getId()).orElse(null));
            }
            List<ProductDto> productDtos = products.stream().map(product -> getProductDto(product)).collect(Collectors.toList());
            return productDtos;
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }
}
