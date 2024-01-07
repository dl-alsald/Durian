package com.durianfirst.durian.repository;

import com.durianfirst.durian.constaint.OrderStatus;
import com.durianfirst.durian.entity.*;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Commit
    @Transactional
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Order order=Order.builder()
                    .member(Member.builder().mid("member"+i).build())
                    .totalPrice(i*1000)
                    .build();
            Order result=orderRepository.save(order);

        });
    }

    @Commit
    @Transactional
    @Test
    public void testOrderItemInsert() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            OrderItem orderitem=OrderItem.builder()
                    .order(Order.builder().ono((long) i).build())
                    .item(Item.builder().ino((long) i).build())
                    .build();
            OrderItem result=orderItemRepository.save(orderitem);

        });
    }
}
