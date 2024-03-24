package com.ssg.wsmt.mgtOrders.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.wsmt.mgtOrders.DTO.MgtOrdersDTO;
import com.ssg.wsmt.mgtOrders.domain.MgtOrders;
import com.ssg.wsmt.mgtOrders.service.MgtOrdersService;
import com.ssg.wsmt.mgtOrders.service.impl.MgtOrdersServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

//    @PostMapping("/AddItems")
//    public ResponseEntity<Void> addItems(@RequestParam(value = "id", required = false) String id,
//                                         @RequestParam(value = "itemNames", required = false) Long[] itemNames,
//                                         @RequestParam(value = "quantities", required = false) Long[] quantities) {
//        log.info(id + " " + itemNames + " " + quantities);
//        System.out.println("Order ID: " + id);
//        if (itemNames != null && quantities != null) {
//            for (int i = 0; i < Math.min(itemNames.length, quantities.length); i++) {
//                System.out.println("Item: " + itemNames[i] + ", Quantity: " + quantities[i]);
//            }
//        }
//
//        mgtOrdersService.addItems(quantities, itemNames, Long.parseLong(id));
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping("/AddItems")
//    public ResponseEntity<Void> addItems(@RequestParam(value = "id", required = false) String id,
//                                         @RequestParam(value = "itemNames", required = false) Long[] itemNames,
//                                         @RequestParam(value = "quantities", required = false) Long[] quantities,
//                                         @RequestBody(required = false) List<Map<String, String>> checkedItemsList) {
//        log.info(id + " " + itemNames + " " + quantities);
//
//        // Process the checked items list
//        if (checkedItemsList != null) {
//            for (Map<String, String> checkedItem : checkedItemsList) {
//                String itemName = checkedItem.get("itemName");
//                String quantity = checkedItem.get("quantity");
//                // Process each checked item as needed
//                mgtOrdersService.addItems(quantities, itemNames, Long.parseLong(id));
//                System.out.println("Item: " + itemName + ", Quantity: " + quantity);
//
//            }
//        }
//
//        // Process the rest of the logic for itemNames and quantities
//
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/AddItems")
    public ResponseEntity<Void> addItems(@RequestBody Map<String, Object> requestData) {
        // Extract checkedItems and id from the requestData
        log.info(requestData);
        List<Map<String, String>> checkedItems = (List<Map<String, String>>) requestData.get("checkedItems");
        String id = String.valueOf(requestData.get("id"));

        // Check for null values and log
        if (checkedItems == null || id == null) {
            log.error("Received null checked items or id");
            return ResponseEntity.badRequest().build(); // Return bad request status
        }

        // Process the checked items list
        for (Map<String, String> checkedItem : checkedItems) {
            String itemName = checkedItem.get("itemName");
            String quantity = checkedItem.get("quantity");

            // Check for null or invalid numeric values
            if (itemName == null || quantity == null) {
                log.error("Null item name or quantity received");
                return ResponseEntity.badRequest().build(); // Return bad request status
            }

            try {
                // Parse itemName and quantity to Long
                long itemId = Long.parseLong(itemName);
                long itemQuantity = Long.parseLong(quantity);

                // Process each checked item
                mgtOrdersService.addItems(itemQuantity, itemId, Long.parseLong(id));

                // Log item details
                log.info("Item Name: " + itemName + ", Quantity: " + quantity);
            } catch (NumberFormatException e) {
                log.error("Invalid numeric value received for item ID or quantity");
                return ResponseEntity.badRequest().build(); // Return bad request status
            }
        }

        // Process the rest of the logic if needed

        return ResponseEntity.ok().build();
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
