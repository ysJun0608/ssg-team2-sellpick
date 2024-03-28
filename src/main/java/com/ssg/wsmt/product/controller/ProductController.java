package com.ssg.wsmt.product.controller;

import com.ssg.wsmt.product.domain.BrandVO;
import com.ssg.wsmt.product.dto.ProductDTO;
import com.ssg.wsmt.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/product")
@Log4j2
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 리스트
    @GetMapping("/list")
    public String list(@RequestParam(required = false) String name, Model model) {
        log.info("product list");
        if (name == null) {
            model.addAttribute("dtoList", productService.getAll(""));
        } else {
            model.addAttribute("dtoList", productService.getAll(name));
        }
        return "/Product/list";

    }

    // 상품 등록
    @GetMapping("/register")
    public void register() {
        log.info("product register ~~이야");
    }

    @PostMapping("/register")
    public String register(@Valid ProductDTO productDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("Post product register이야~");
        if (bindingResult.hasErrors()) {
            log.info("has errors ...");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/product/register";
        }

        BrandVO brandVO = productService.getBrandById(productDTO.getBrandId());

        log.info(productDTO);
        productService.register(productDTO);
        return "redirect:/product/list";
    }

    // 상품 상세페이지
    @GetMapping({"/read", "/modify"})
    public void read(@RequestParam long id, Model model) {
        ProductDTO productDTO = productService.getOne(id);
        model.addAttribute("dto", productDTO);
//        return "product/read";
    }


    // 상품 수정
    @PostMapping("/modify")
    public String modifyComplete(@Valid ProductDTO productDTO, BindingResult bindingResult) {
//        if(bindingResult.hasErrors()){
//            return "redirect:/product/modify?id=" + productDTO.getId();
//        }
        log.info("수정 포스트야~~");
        productService.modify(productDTO);
        return "redirect:/product/list";
    }

    // 상품 삭제
    @PostMapping("/remove")
    public String remove(long id, RedirectAttributes redirectAttributes) {
        log.info(id);
        productService.remove(id);
        return "redirect:/product/list";
    }
}