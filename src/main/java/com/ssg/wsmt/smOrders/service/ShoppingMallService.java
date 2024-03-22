package com.ssg.wsmt.smOrders.service;

import com.ssg.wsmt.smOrders.domain.ShoppingMallVo;
import com.ssg.wsmt.smOrders.dto.ShoppingMallDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingMallService {
    Long chooseShoppingMall();
    ShoppingMallDTO getShoppingMallDetails();
    List<ShoppingMallVo> findAllShoppingMalls(ShoppingMallDTO shoppingMallDTO);

}
