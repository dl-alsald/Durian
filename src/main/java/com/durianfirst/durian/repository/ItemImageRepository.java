package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.Item;
import com.durianfirst.durian.entity.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ItemImageRepository extends JpaRepository<ItemImage, Long>, QuerydslPredicateExecutor<Item> {

    List<ItemImage> findByItemInoOrderByIdAsc(Long ItemId);

    /*ItemImg findByPnoAndIimgrep(Long pno, String iimgrep); // 상품의 대표 이미지를 찾음*/

    List<ItemImage> findByItemIdOrderByIdAsc(Long ItemId);

    ItemImage findByIdAndIimgrep(Long ino, String iimgrep); // 상품의 대표 이미지를 찾음
}
