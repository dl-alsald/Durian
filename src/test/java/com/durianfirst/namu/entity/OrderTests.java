package com.durianfirst.namu.entity;


import com.durianfirst.namu.repository.MemberRepository;
import com.durianfirst.namu.repository.OrderItemRepository;
import com.durianfirst.namu.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import java.util.stream.IntStream;


@SpringBootTest
@Transactional
public class OrderTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void cascadeTest(){

        Order order = new Order();
        Item item = new Item();

        IntStream.range(1, 10).forEach(i -> {
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .oicount(10)
                    .oiprice(1000)
                    .order(order)
                    .build();
            System.out.println(orderRepository.save(order));
        });


    }

    public Order createOrder(){
        Order order = new Order();
        Item item = new Item();

        IntStream.range(0, 3).forEach(i -> {
            OrderItem orderItem = OrderItem.builder()
                    .item(item)
                    .oicount(10)
                    .oiprice(1000)
                    .order(order)
                    .build();
            System.out.println(orderRepository.save(order));
        });

        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        orderRepository.save(order);
        return order;

    }

    @Test
    @DisplayName("고아객체 제거")
    public void orphanRemovalTest(){
        Order order = this.createOrder();
        order.getOrderItems().remove(0);
    }



}
