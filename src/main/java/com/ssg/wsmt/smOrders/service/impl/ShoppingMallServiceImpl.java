package com.ssg.wsmt.smOrders.service.impl;

import com.ssg.wsmt.inventory.dao.WhSmRelationshipDao;
import com.ssg.wsmt.smOrders.dao.ShoppingMallDao;
import com.ssg.wsmt.smOrders.domain.ShoppingMall;
import com.ssg.wsmt.smOrders.dto.ShoppingMallDTO;
import com.ssg.wsmt.smOrders.mapper.ShoppingMallMapper;
import com.ssg.wsmt.smOrders.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
@Service
@Log4j2 // 로그를 위한 Lombok 어노테이션
@RequiredArgsConstructor // 해줘야 ShoppingMallMapper 가능했음 private final
public class ShoppingMallServiceImpl implements ShoppingMallService {

//    private final ShoppingMallMapper shoppingMallMapper;

    private final ShoppingMallMapper shoppingMallMapper;

    @Override
    public List<ShoppingMall> findAllShoppingMalls(ShoppingMallDTO shoppingMallDTO) {
        ShoppingMall shoppingMall = ShoppingMall.builder()
                .id(shoppingMallDTO.getId()) // get 메서드로 필드 값에 접근, dto는 TodoDTO 타입의 인스턴스를 가정
                .name(shoppingMallDTO.getName())
                .build();

        return shoppingMallMapper.findAll();
    }

//    @Override
//    public List<ShoppingMall> findAllShoppingMalls() {
//        return shoppingMallMapper.findAll();
//    }

    @Override
    public ShoppingMallDTO getShoppingMallDetails() {
//        return shoppingMallMapper.findShoppingMallDetails();
        return null;
    }






//    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//    ShoppingMallDao shoppingMallDao = new ShoppingMallDao();
//    WhSmRelationshipDao whSmRelationshipDao = new WhSmRelationshipDao();
//
//
//
    @Override
    public Long chooseShoppingMall() {
//        List<ShoppingMall> shoppingMallList = shoppingMallDao.findAll();
//
//        if (shoppingMallList.isEmpty()) {
//            System.out.println("쇼핑몰이 존재하지 않습니다.");
//            return null;
//        }
//
//        System.out.println("=".repeat(50));
//        System.out.printf("| %s  | %s \n", "쇼핑몰번호", "쇼핑몰이름");
//        for (ShoppingMall sm : shoppingMallList) {
//            System.out.printf("| %-10d| %s \n", sm.getId(), sm.getName());
//        }
//
//        Long smNum = null;
//        try {
//            System.out.println("=".repeat(50));
//            System.out.println("창고와 연동할 쇼핑몰번호를 입력해주세요.");
//            smNum = Long.valueOf(input.readLine());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return smNum;
        return null;
    }



}
