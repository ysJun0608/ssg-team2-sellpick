package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.*;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.inventory.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final WarehouseService warehouseService;
    private final InventoryService inventoryService;
    
    @PostMapping("/warehouseCreate")
    public String postCreate(@ModelAttribute WarehouseCreateDTO warehouseDTO) {
        log.info("생성이 제대로 되었나? ");
        log.info(warehouseDTO);

        return "redirect:/inventory/main";
    }

    @GetMapping("/warehouseCreate")
    public void warehouse(Model model) {
        log.info("warehouse...");
    }



    @GetMapping("/warehouselist")
    public void warehouseList(@Valid PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.readAllWarehouseWithSection(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        log.info("warehouseList SUCESS");
        log.info(responseDTO.getDtoList());
        log.info(pageRequestDTO.toString());
    }


    @GetMapping("/list")
    public String listInventory(@RequestParam(value = "warehouseId", required = false) Long warehouseId, Model model) {
        List<InventoryDTO> inventoryList;

        if (warehouseId == null) {
            inventoryList = inventoryService.findAll();
        } else {
            inventoryList = inventoryService.findByWarehouseId(warehouseId);
        }
        model.addAttribute("inventoryList", inventoryList);
        return "/Inventory/inventoryList";
    }

    @GetMapping("/list/{warehouseId}")
    public String listInventoryById(@PathVariable Long warehouseId, Model model) {
        List<InventoryDTO> inventoryList;
        log.info("getmapping method");
        inventoryList = inventoryService.findByWarehouseId(warehouseId);
        model.addAttribute("inventoryList", inventoryList);
        return "/Inventory/inventoryList";
    }
    @GetMapping("/inventory/list")
    public String showInventoryListPage(){

        return "/inventoryList";
    }
}