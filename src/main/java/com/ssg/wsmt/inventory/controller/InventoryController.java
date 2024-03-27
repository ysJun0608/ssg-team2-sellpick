package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.InventoryDTO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.WarehouseCreateDTO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.inventory.service.WarehouseService;
import com.ssg.wsmt.product.dto.ProductDTO;
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
//        warehouseService.createWarehouse(warehouseDTO);


        return "redirect:/inventory/main";
    }

    @GetMapping("/warehouseCreate")
    public void warehouse(Model model) {
        log.info("warehouse...");
    }



    @GetMapping("/warehouselist")
    public void warehouseCreate(@Valid PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.readAllWarehouseWithSection(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        log.info("warehouseList SUCESS");
        log.info(responseDTO.getDtoList());
        log.info(pageRequestDTO.toString());
    }

//    @PostMapping("/warehouselist")
//    public void warehousePost(@Valid Model model) {
//        model.addAttribute("responseDTO",warehouseService.readAllWarehouse());
//        log.info(warehouseService.readAllWarehouse());
//        log.info("warehouseCreate");
//
//    }
//
//    @GetMapping("/warehouseChange")
//    public void warehousesearch(@Valid BindingResult bindingResult, Model model) {
//        model.addAttribute("responseDTO",warehouseService.readAllWarehouse());
//        log.info("warehouseSearch");
//    }

//    @GetMapping("/order/list")
//    public String findAllProducts(Model model) {
//        List<ProductDTO> productList = inventoryService.findAllProducts();
//        model.addAttribute("productList", productList);
//        return "productList";
//    }


//@GetMapping("/list")
//    public String list(PageRequestDTO pageRequestDTO, Model model,
//                       @RequestParam(value = "warehouseId", required = false) Long warehouseId) {
//        log.info("Requested page: {}, size: {}, warehouseId: {}",
//                pageRequestDTO.getPage(), pageRequestDTO.getSize(), warehouseId);
//        List<InventoryDTO> inventoryList;
//
//        if (warehouseId != null) {
//            // warehouseId가 null이 아닌 경우 해당 창고의 재고 목록을 조회합니다.
//            inventoryList = inventoryService.findByWarehouseId(warehouseId);
//        } else {
//            // warehouseId가 null이면 전체 재고 목록을 조회합니다.
//            inventoryList = inventoryService.findAll();
//        }
//
//        model.addAttribute("inventoryList", inventoryList);
//        model.addAttribute("pageRequestDTO", pageRequestDTO);
//
//        return "inventory/inventoryList";
//    }



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