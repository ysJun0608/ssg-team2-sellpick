package com.ssg.wsmt.product.controller;

import com.ssg.wsmt.product.dto.ProductsDTO;
import com.ssg.wsmt.product.service.ProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductsService productsService;

    @GetMapping("/test")
    public String test() {
        log.info("register이야");
        return "product/test";
    }

    @GetMapping("/register")
    public String register() {
        log.info("register이야");
        return "product/product";
    }



    @PostMapping("/register")
    public String register(ProductsDTO productsDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("나포스트레지스터");
        log.info(productsDTO);
//        if (bindingResult.hasErrors()) {
//            log.info("has errors ...");
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:product/test";
//        }

        log.info(productsDTO);
        productsService.register(productsDTO);
        return "redirect:/product/list";
    }
}
