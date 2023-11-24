package com.durianfirst.namu.repository;

import com.durianfirst.namu.dto.CartDetailDTO;
import com.durianfirst.namu.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartCidAndItemId(Long cid, Long id);

    @Query("select  new com.mall.twins.twinsmall.dto.CartDetailDto(ci.cino, i.pname, i.pprice, ci.count, im.iimgurl)" +
            "from CartItem ci, ItemImg im join ci.item i where ci.cart.cid = :cid and im.item.id = ci.item.id and im.iimgrep = 'Y' order by ci.regTime desc ")
    List<CartDetailDTO> findCartDetailDtoList(long cid);
}
