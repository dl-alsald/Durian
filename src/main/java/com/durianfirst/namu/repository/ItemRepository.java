package com.durianfirst.namu.repository;


import com.durianfirst.namu.entity.Item;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    @Query("select i, ii from Item i left outer join ItemImg ii on ii.item = i")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select i, ii" +
            " from Item i left outer join ItemImg ii on ii.item = i " +
            " where i.ino = :ino group by ii")
    List<Object[]> getItemWithAll(Long ino);

}
