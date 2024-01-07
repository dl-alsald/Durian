package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.Cart;
import com.durianfirst.durian.entity.Member;
import com.durianfirst.durian.entity.Shipping;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ShippingRepositoryTests {

    @Autowired
    private ShippingRepository shippingRepository;


    @Commit
    @Transactional
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Shipping shipping = Shipping.builder()
                    .member(Member.builder().mid("member" + i).build())
                    .sname("배송지명" + i)
                    .sperson("김스규" + i)
                    .szonecode("1111" + i)
                    .address("수원" + i)
                    .saddress("영통" + i)
                    .sphone("01011112222" + i)
                    .sdefault(true)

                    .build();
            Shipping result = shippingRepository.save(shipping);
        });
    }
}
