package com.ssg.wsmt.mgtOrders.controller;

import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.service.MgtOrdersService;
import com.ssg.wsmt.mgtOrders.service.impl.MgtOrdersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/MgtOrders")
@RequiredArgsConstructor
public class MgtOrdersController {

    @Autowired
    MgtOrdersServiceImpl mgtOrdersService;

    @GetMapping("/MgtOrderCreate")
    public void showCreate(Long id, Model model) {
       log.info("CreatePage....");
       model.addAttribute("id", id);
    }

    @PostMapping("/MgtOrderCreate")
    public String create(MgtOrdersDTO mgtOrdersDTO, Model model) {
        log.info("Create Post ....");
        log.info(mgtOrdersDTO);
        Long id = mgtOrdersService.add(mgtOrdersDTO);
        log.info(id);
        return "redirect:/MgtOrders/MgtOrderCreate?id=" + id;
    }

    @GetMapping("/MgtOrderConfirm")
    public void confirm() {
        log.info("confirm....");
    }

    @GetMapping("/MgtOrderSearch")
    public void search() {
        log.info("confirm....");
    }
    @GetMapping("/MgtOrderNondeliverd")
    public void searchNondeliverd() {
        log.info("confirm....");
    }

    @GetMapping("/index")
    public void getIndex() {
        log.info("confirm....");
    }
}
