package com.durianfirst.namu.repository;


import com.durianfirst.namu.entity.Item;
import com.durianfirst.namu.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long>,
        QuerydslPredicateExecutor<Item> {

    List<ItemImg> findByItemInoOrderByIdAsc(Long ItemId);

    /*ItemImg findByPnoAndIimgrep(Long pno, String iimgrep); // 상품의 대표 이미지를 찾음*/

}
