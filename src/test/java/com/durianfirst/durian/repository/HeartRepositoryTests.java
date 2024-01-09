package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.Heart;
import com.durianfirst.durian.entity.Item;
import com.durianfirst.durian.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class HeartRepositoryTests {

    @Autowired
    private HeartRepository heartRepository;

    @Test
    public void insertDum() {
        IntStream.rangeClosed(1,100).forEach(i -> {

            Heart heart = Heart.builder()
                    .hno((long) (i*1))
                    .member(Member.builder().mid("useruser").build())
                    .item(Item.builder().ino(1L).build())
                    .build();
            System.out.println(heartRepository.save(heart));
        });
    }
}
