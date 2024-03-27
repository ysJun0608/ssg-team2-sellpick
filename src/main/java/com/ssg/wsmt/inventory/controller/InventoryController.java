package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.WarehouseCreateDTO;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.inventory.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/inventory")
@Log4j2
@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;
    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model,
                       @RequestParam(value = "warehouseId", required = false) Long warehouseId) {
        log.info("Requested page: {}, size: {}, warehouseId: {}",
                pageRequestDTO.getPage(), pageRequestDTO.getSize(), warehouseId);

        List<InventoryDTO> inventoryList;

        if (warehouseId != null) {
            // warehouseId가 null이 아닌 경우 해당 창고의 재고 목록을 조회합니다.
            inventoryList = inventoryService.findByWarehouseId(warehouseId);
        } else {
            // warehouseId가 null이면 전체 재고 목록을 조회합니다.
            inventoryList = inventoryService.findAll();
        }

        model.addAttribute("inventoryList", inventoryList);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "inventory/inventoryList";
    }



//    @GetMapping("/list")
//    public String listInventory(@RequestParam(value = "warehouseId", required = false) Long warehouseId, Model model) {
//        log.info(inventoryService.findAll());
//        List<InventoryDTO> inventoryList;
//
//        if (warehouseId == null) {
//            inventoryList = inventoryService.findAll();
//        } else {
//            inventoryList = inventoryService.findByWarehouseId(warehouseId);
//        }
//        model.addAttribute("inventoryList", inventoryList);
//        return "inventory/inventoryList";
//    }

    @GetMapping("/list/{warehouseId}")
    public String listInventoryById(@PathVariable Long warehouseId, Model model) {
        log.info(inventoryService.findByWarehouseId(warehouseId));
        List<InventoryDTO> inventoryList;
        log.info("getmapping method");
        inventoryList = inventoryService.findByWarehouseId(warehouseId);
        model.addAttribute("inventoryList", inventoryList);
        return "inventory/inventoryList";
    }
    @GetMapping("/inventory/list")
    public String showInventoryListPage(){

        return "inventoryList";
    }

    @PostMapping("/warehouseCreate")
    public String postCreate(@ModelAttribute WarehouseCreateDTO warehouseDTO) {
        log.info("생성이 제대로 되었나? ");
        log.info(warehouseDTO);
//        warehouseService.createWarehouse(warehouseDTO);
        return "redirect:/inventory/main";
    }

    @GetMapping("/warehouseCreate")
    public void warehouse(Model model) {
        log.info("warehouse...");
    }

    @GetMapping("/warehouselist")
    public void warehouseCreate(@Valid Model model) {
        model.addAttribute("responseDTO",warehouseService.readAllWarehouse());
        log.info(warehouseService.readAllWarehouse());
        log.info("warehouseCreate");

    }
    @PostMapping("/warehouselist")
    public void warehousePost(@Valid Model model) {
        model.addAttribute("responseDTO",warehouseService.readAllWarehouse());
        log.info(warehouseService.readAllWarehouse());
        log.info("warehouseCreate");

    }

    @GetMapping("/warehouseChange")
    public void warehousesearch(@Valid BindingResult bindingResult, Model model) {
        model.addAttribute("responseDTO",warehouseService.readAllWarehouse());
        log.info("warehouseSearch");

    }
}
