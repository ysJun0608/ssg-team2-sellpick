package com.ssg.wsmt.smOrders.service;

import com.ssg.wsmt.smOrders.domain.ShoppingMall;
import com.ssg.wsmt.smOrders.dto.ShoppingMallDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingMallService {
    Long chooseShoppingMall();
    ShoppingMallDTO getShoppingMallDetails();
    List<ShoppingMall> findAllShoppingMalls(ShoppingMallDTO shoppingMallDTO);

}
