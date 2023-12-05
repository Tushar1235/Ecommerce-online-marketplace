package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.model.AuthenticationToken;
import com.ecommerce.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthenticationTokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);

    AuthenticationToken findByToken(String token);
}
