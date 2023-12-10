package com.durianfirst.durian.service;

import com.durianfirst.durian.dto.OrderDTO;
import com.durianfirst.durian.dto.OrderHistDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
//@RequiredArgsConstructor
public interface OrderService {

    Long order(OrderDTO orderDto, String mid);

    Page<OrderHistDTO> getOrderList(String mid, Pageable pageable);

    Boolean validateOrder(Long ono,String mid);

    void cancelOrder(Long ono);

    Long orders(List<OrderDTO> orderDtoList, String mid);

    Page<OrderHistDTO> getOrderListByOrderId(String mid, Long ono, Pageable pageable);


}
