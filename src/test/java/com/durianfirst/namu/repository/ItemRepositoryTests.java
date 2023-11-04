package com.durianfirst.namu.repository;

import com.durianfirst.namu.entity.Item;
import com.querydsl.core.BooleanBuilder;
import ognl.BooleanExpression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class ItemRepositoryTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void insertDummies(){

        IntStream.range(1, 50).forEach(i -> {
            Item item = Item.builder()
                    .iname("상품명" + i)
                    .iprice(1000*i)
                    .idealway("택배")
                    .ilocation("수원"+i)
                    .icate("의류")
                    .icondition("A")
                    .idesc("상세설명"+i)
                    .build();
            System.out.println(itemRepository.save(item));
        });

    }

    @Test
    public void updateTest(){

        Optional<Item> result = itemRepository.findById(1L);

        if(result.isPresent()){

            Item item = result.get();

            item.changeIname("상품명꼬요");
            item.changeIprice(200000);

            itemRepository.save(item);
        }
    }

   /* @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("ino").descending());

        QItem qitem = QItem.item;

        String keyword = "1";

        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression = qitem.iname.contains(keyword);

        builder.and(expression);

        Page<Item> result = itemRepository.findAll(builder, pageable);

        result.stream().forEach(item -> {
            System.out.println(item);
        });



    }*/


}
