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
    public String addItems(@RequestParam(value = "id", required = false) Long id,
                           @RequestParam(value = "itemNames", required = false) Long[] itemNames,
                           @RequestParam(value = "quantities", required = false) Long[] quantities) {
        log.info(id + " " + itemNames + " " + quantities);
        System.out.println("Order ID: " + id);
        if (itemNames != null && quantities != null) {
            for (int i = 0; i < Math.min(itemNames.length, quantities.length); i++) {
                System.out.println("Item: " + itemNames[i] + ", Quantity: " + quantities[i]);
            }
        }

        mgtOrdersService.addItems(quantities, itemNames, id);
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

    // Define the endpoint for deleting items
    @ResponseBody
    @PostMapping("/DeleteItems")
    public String deleteItems(@RequestBody String formData) {
        log.info(formData);
        // Here, you'll implement the logic to parse the form data
        // and delete the selected items from the database

        // For demonstration purposes, let's assume formData contains the IDs of items to be deleted
        // You would need to extract these IDs and perform the deletion operation

        // Implement your logic here...

        // Return a response indicating the success or failure of the operation
        return "Items deleted successfully"; // You can customize the response message as needed
    }
}
