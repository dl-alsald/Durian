package com.durianfirst.namu.service;

import com.durianfirst.namu.dto.CartDetailDTO;
import com.durianfirst.namu.dto.CartItemDTO;

import java.util.List;

@Service
@Transactional
public interface CartService {

    Long addCart(CartItemDTO cartItemDto, String mid);

    List<CartDetailDTO> getCartList(String mid);

    boolean validateCartItem(Long cartItemId, String mid);

    void updateCartItemCount(Long cartItemId, int count);

    void deleteCartItem(Long cartItemId);

    Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String mid);
}