package com.durianfirst.namu.service;

import com.durianfirst.namu.dto.ItemDTO;
import com.durianfirst.namu.dto.PageRequestDTO;
import com.durianfirst.namu.dto.PageResultDTO;
import com.durianfirst.namu.entity.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTests {

    @Autowired
    private ItemService itemService;

    @Test
    public void testRegister(){

        ItemDTO itemDTO = ItemDTO.builder()
                .iname("test name")
                .iprice(10000)
                .idealway("test Dealway")
                .ilocation("test Location")
                .icate("test cate")
                .icondition("test condition")
                .idesc("test desc")
                .build();

        System.out.println(itemService.register(itemDTO));
    }

    @Test
    public void testList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResultDTO<ItemDTO, Object[]> resultDTO = itemService.getList(pageRequestDTO);

            System.out.println("PREV: " + resultDTO.isPrev());
            System.out.println("NEXT: " + resultDTO.isNext());
            System.out.println("TOTAL: " + resultDTO.getTotalPage());

            System.out.println("-----------------------");
            for(ItemDTO itemDTO : resultDTO.getDtoList()) {
            System.out.println(itemDTO);

            System.out.println("========================");
            resultDTO.getPageList().forEach(i -> System.out.println(i));

        }
    }


}
