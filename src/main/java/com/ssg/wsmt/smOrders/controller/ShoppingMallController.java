package com.ssg.wsmt.smOrders.controller;

import com.ssg.wsmt.smOrders.domain.ShoppingMallVo;
import com.ssg.wsmt.smOrders.service.ShoppingMallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/ex")
@Log4j2
@RequiredArgsConstructor
public class ShoppingMallController {

    private final ShoppingMallService shoppingMallService; // 이 필드를 클래스 내에 한 번만 선언해야 합니다.

    @GetMapping("/index")
    public void index(Model model, ShoppingMallVo shoppingMall) {
        log.info("hello~~~");
        model.addAttribute("msg", "hello spring boot 임 ㅋㅋㅋ");
    }
}