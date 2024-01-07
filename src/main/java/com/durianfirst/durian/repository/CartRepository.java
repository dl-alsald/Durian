package com.durianfirst.durian.repository;


import com.durianfirst.durian.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberMid(String mid);
}
