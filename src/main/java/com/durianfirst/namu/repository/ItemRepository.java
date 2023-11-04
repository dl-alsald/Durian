package com.durianfirst.namu.repository;


import com.durianfirst.namu.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Optional<Item> findByIname(@Param("iname") String iname); // Review에서 사용

    /*List<Item> findByIname(String iname);

    List<Item> findByInameOrIdesc(String iname, String idesc);

    List<Item> findByIpriceLessThan(Integer iprice);

    List<Item> findByIpriceLessThanOrderByIpriceDesc(Integer iprice);

    @Query("select i from Item i where i.idesc like %:idesc% order by i.iprice desc")
    List<Item> findByPdesc(@Param("idesc") String idesc);
    //@Param파라미터로 넘어온 값을 JPQL에 들어갈 변수로 지정해 줄 수 있음
    //현재는 itemDetail 변수를 like% ~ % 사이에 :itemDetail 값이 들어가도록 작성

    @Query(value = "select * from item i where i.idesc like %:idesc% order by i.iprice desc", nativeQuery = true)
    List<Item> findByIdescByNative(@Param("idesc") String idesc);*/


}
