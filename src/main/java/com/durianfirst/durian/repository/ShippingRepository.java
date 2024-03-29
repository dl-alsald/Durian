package com.durianfirst.durian.repository;

import com.durianfirst.durian.dto.ShippingDTO;
import com.durianfirst.durian.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ShippingRepository extends JpaRepository<Shipping, Long>, QuerydslPredicateExecutor<Shipping> {

    // mid(유저 아이디)로 Shipping 테이블의 모든 데이터를 가져온다. -> 기본배송지 내림차순
    @Transactional
    @Query("SELECT s FROM Shipping s WHERE s.member.mid = :mid")
    List<Shipping> listOfShipping(String mid);

    @Transactional
    @Query("SELECT s FROM Shipping s WHERE s.member.mid = :mid AND s.sdefault != NULL")
    ShippingDTO getDefaultAddress(String mid);

    // sno(기본키)로 데이터를 가져온다.
    Optional<Shipping> findBySno(Long sno);

    @Modifying
    @Transactional
    @Query("UPDATE Shipping s SET s.sdefault = NULL WHERE s.member.mid = :mid")
    void updateDefaultShipping(@Param("mid") String mid);

    /*List<Shipping> findAllByMid(String mid);*/


    /*// 기본 배송지 설정 시 나머지 데이터를 기본 배송지에서

    Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Shipping s SET s.sdefault = NULL WHERE s.member.mid = :mid")
    void updateSdefault(@Param("mid") String mid);*/


}
