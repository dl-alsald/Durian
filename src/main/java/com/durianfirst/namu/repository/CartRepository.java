package com.durianfirst.namu.repository;

import com.durianfirst.namu.entity.Cart;
import com.durianfirst.namu.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public class CartRepository extends JpaRepository<CartItem, Long> {
    Cart findByMemberMno(Long mno);
}
