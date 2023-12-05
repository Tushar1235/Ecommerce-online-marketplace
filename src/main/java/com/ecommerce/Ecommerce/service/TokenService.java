package com.ecommerce.Ecommerce.service;

import com.ecommerce.Ecommerce.model.AuthenticationToken;
import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.repository.AuthenticationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    AuthenticationTokenRepo tokenRepo;
    public void saveToken(AuthenticationToken token){
        tokenRepo.save(token);
    }

    public AuthenticationToken getToken(User user){
        return tokenRepo.findByUser(user);
    }
}
