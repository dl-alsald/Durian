package com.durianfirst.durian.repository;

import com.durianfirst.durian.entity.Heart;
import com.durianfirst.durian.entity.Item;
import com.durianfirst.durian.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long>/*, QuerydslPredicateExecutor<Heart>*/ {
    Optional<Integer> countByItem(Item item);

    Optional<Heart> findByMemberAndItem(Member member, Item item);
}
