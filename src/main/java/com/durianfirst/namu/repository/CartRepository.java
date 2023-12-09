package com.durianfirst.namu.repository;

import com.durianfirst.namu.entity.Cart;
import com.durianfirst.namu.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberMno(Long mno);
}
