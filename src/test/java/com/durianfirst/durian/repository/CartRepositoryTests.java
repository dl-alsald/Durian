package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class CartRepositoryTests {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Commit
    @Transactional
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Cart cart=Cart.builder()
                    .member(Member.builder().mid("member"+i).build())
                    .build();
            Cart result = cartRepository.save(cart);
        });
    }

    @Commit
    @Transactional
    @Test
    public void testCartItemInsert() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            CartItem cartItem=CartItem.builder()
                    .cart(Cart.builder().cid((long) (i)).build())
                    .item(Item.builder().ino((long) (i)).build())
                    .build();
            CartItem result = cartItemRepository.save(cartItem);
        });
    }
}
