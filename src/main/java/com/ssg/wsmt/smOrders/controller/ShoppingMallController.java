package com.ssg.wsmt.smOrders.controller;

import com.ssg.wsmt.smOrders.domain.ShoppingMall;
import com.ssg.wsmt.smOrders.dto.ShoppingMallDTO;
import com.ssg.wsmt.smOrders.service.ShoppingMallService;
import com.ssg.wsmt.smOrders.service.SmOrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.*;
@Controller
//@RequestMapping("/ex")
@Log4j2
@RequiredArgsConstructor
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService; // 이 필드를 클래스 내에 한 번만 선언해야 합니다.


    @GetMapping("/ex/ex1")
    public String listShoppingMalls(Model model, ShoppingMallDTO shoppingMallDTO) {
        model.addAttribute("shoppingMalls", shoppingMallService.findAllShoppingMalls(shoppingMallDTO));
        return "ex/ex1"; // templates/ex/ex1.html
    }




    @GetMapping("/ex/test")
    public void selectList(Model model, ShoppingMall shoppingMall) { // URL에서 pno 파라미터를 받아옵니다.
//        ShoppingMall shoppingMall = shoppingMallService.selectList(shoppingMall); // 서비스에서 쇼핑몰 정보를 조회합니다.
//        log.info("shoppingMall~~~~~~~~~~``" +shoppingMall);
//        model.addAttribute("dto", shoppingMall); // 모델에 데이터 추가
    }


    @GetMapping("/hello")
    public void hello(Model model) {
        log.info("hello~~~");
        model.addAttribute("msg", "hello spring boot 임 ㅋㅋㅋ");
    }




}