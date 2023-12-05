package com.ecommerce.Ecommerce.repository;

import com.ecommerce.Ecommerce.model.User;
import com.ecommerce.Ecommerce.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WIshlistRepository extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findAllByUserOrderByCreatedDateDesc(User user);

}
