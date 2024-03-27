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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    MgtOrdersService mgtOrdersService;

    @GetMapping("/MgtOrderCreate")
    public void showCreate(Long id, Model model) {
        log.info("CreatePage....");
        model.addAttribute("id", id);
    }

    @GetMapping("/home")
    public String home() {
        log.info("");
        return "home";
    }

    @GetMapping("/example")
    public String example() {
        log.info("");
        return "example";
    }

    @PostMapping("/MgtOrderCreate")
    public ResponseEntity<?> create(@RequestBody MgtOrdersDTO mgtOrdersDTO) {
        try {
            log.info("Create Post ....");
            log.info(mgtOrdersDTO);
            Long id = mgtOrdersService.createForm(mgtOrdersDTO);
            log.info(id);
            // Construct the JSON response
            return ResponseEntity.ok().body("{\"id\": " + id + "}");
        } catch (Exception e) {
            // Handle any exceptions and return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred.\"}");
        }
    }


    @PostMapping("/AddItems")
    public ResponseEntity<Map<String, String>> addItems(@RequestBody Map<String, Object> requestData) {
        // Extract checkedItems and id from the requestData
        log.info("requestData : " + requestData);
        List<Map<String, String>> checkedItems = (List<Map<String, String>>) requestData.get("checkedItems");
        String id = String.valueOf(requestData.get("id"));
        log.info("addItems Id : " + id);
        // Check for null values and log
        if (checkedItems == null || id == null) {
            log.error("Received null checked items or id");
            return ResponseEntity.badRequest().build(); // Return bad request status
        }

        mgtOrdersService.deleteItems(Long.parseLong(id));

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
        log.info("AddItems Success");

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", id);

        return ResponseEntity.ok(responseBody);
    }



    @GetMapping("/MgtOrderConfirm")
    public void confirm() {
        log.info("confirm....");
    }

    @GetMapping("/MgtOrderSearch")
        public void search(Model model) {
        List<MgtOrdersDTO> mgtOrdersDTOList = mgtOrdersService.selectAll();
        log.info("mgtOrdersDTOList" + mgtOrdersDTOList);
        model.addAttribute("mgtOrdersDTOList", mgtOrdersDTOList);
    }


    @PostMapping("/MgtOrderSearch")
    public String searchOrders(
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false) String purchaser,
            @RequestParam(required = false) String warehouseId,
            Model model) {
        // Add your logic here to process the search parameters and retrieve the data
        // For demonstration purposes, let's just return a success message

        log.info("MgtOrderSeach PostMapping");
        // Dummy data for demonstration
        List<MgtOrdersDTO> mgtOrdersDTOList = new ArrayList<>();
        // Add your logic to fetch data based on search parameters
        // For example:
        log.info("start date " + startDate);
        log.info("end date : " + endDate);
        log.info("purchaser : " + purchaser);
        log.info("warehouseId : " + warehouseId);
         mgtOrdersDTOList = mgtOrdersService.searchOrders(startDate, endDate, purchaser, warehouseId);
        // Pass the data to the Thymeleaf template

        log.info("mgtOrdersDTOLIst" + mgtOrdersDTOList);
        model.addAttribute("mgtOrdersDTOList", mgtOrdersDTOList);

        // Return the name of your Thymeleaf view template
        return "/MgtOrders/MgtOrderSearch";
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
