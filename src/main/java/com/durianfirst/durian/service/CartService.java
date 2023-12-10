package com.durianfirst.durian.service;


import com.durianfirst.durian.dto.CartDetailDTO;
import com.durianfirst.durian.dto.CartItemDTO;
import com.durianfirst.durian.dto.CartOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface CartService {

    Long addCart(CartItemDTO cartItemDto, String mid);

    List<CartDetailDTO> getCartList(String mid);

    boolean validateCartItem(Long cartItemId, String mid);

    void updateCartItemCount(Long cartItemId, int count);

    void deleteCartItem(Long cartItemId);

    Long orderCartItem(List<CartOrderDTO> cartOrderDtoList, String mid);
}