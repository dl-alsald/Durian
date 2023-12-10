package com.durianfirst.durian.repository;

import com.durianfirst.durian.dto.CartDetailDTO;
import com.durianfirst.durian.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartCidAndItemId(Long cid, Long id);

    @Query("select new CartDetailDto(ci.cino, i.iname, i.iprice, ci.count, im.path)" +
            "from CartItem ci, ItemImage im join ci.item i where ci.cart.cid = :cid and im.item.ino = ci.item.ino and im.iimgrep = 'Y' order by ci.regDate desc ")
    List<CartDetailDTO> findCartDetailDtoList(long cid);
}
