package com.ssg.wsmt.smOrders.service.impl;

import com.ssg.wsmt.smOrders.mapper.ShoppingMallMapper;
import com.ssg.wsmt.smOrders.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
@Service
@Log4j2 // 로그를 위한 Lombok 어노테이션
@RequiredArgsConstructor // 해줘야 ShoppingMallMapper 가능했음 private final
public class ShoppingMallServiceImpl implements ShoppingMallService {

    private final ShoppingMallMapper shoppingMallMapper;

    @Override
    public Long chooseShoppingMall() {
        return null;
    }

}
