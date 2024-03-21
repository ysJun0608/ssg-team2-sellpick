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
import org.springframework.web.bind.annotation.*;

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
        Long id = mgtOrdersService.createForm(mgtOrdersDTO);
        log.info(id);
        return "redirect:/MgtOrders/MgtOrderCreate?id=" + id;
    }

    @PostMapping("/AddItems")
    public String addItems(@RequestParam(value = "id", required = false) String id,
                           @RequestParam(value = "itemNames", required = false) String[] itemNames,
                           @RequestParam(value = "quantities", required = false) Integer[] quantities) {
        log.info(id + " " + itemNames + " " + quantities);
        System.out.println("Order ID: " + id);
        if (itemNames != null && quantities != null) {
            for (int i = 0; i < Math.min(itemNames.length, quantities.length); i++) {
                System.out.println("Item: " + itemNames[i] + ", Quantity: " + quantities[i]);
            }
        }
        return "redirect:/MgtOrders/MgtOrderCreate";
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
