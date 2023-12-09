package com.durianfirst.namu.repository;

import com.durianfirst.namu.dto.CartDetailDTO;
import com.durianfirst.namu.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartCidAndItemIno(Long cid, Long ino);

   /* @Query("select  CartDetailDTO(ci.cino, i.iname, i.iprice, ci.count, im.path)" +
            "from CartItem ci, ItemImg im join ci.item i where ci.cart.cid = :cid and im.item.ino = ci.item.ino and im.iimgrep = 'Y' order by ci.regTime desc ")
    List<CartDetailDTO> findCartDetailDtoList(long cartItemId);*/


    @Query("SELECT new com.durianfirst.namu.dto.CartDetailDTO(ci.cino, i.iname, i.iprice, ci.count, im.path) " +
            "FROM CartItem ci JOIN ci.item i JOIN ItemImg im ON im.item.ino = ci.item.ino " +
            "WHERE ci.cart.cid = :cartItemId AND im.iimgrep = 'Y' ORDER BY ci.regTime DESC")
    List<CartDetailDTO> findCartDetailDtoList(long cartItemId);

}
