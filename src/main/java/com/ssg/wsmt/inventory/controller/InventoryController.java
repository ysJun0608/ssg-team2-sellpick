package com.ssg.wsmt.inventory.controller;
import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.WarehouseCreateDTO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.inventory.service.WarehouseService;
import com.ssg.wsmt.product.dto.ProductsDTO;
import jakarta.validation.Valid;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final WarehouseService warehouseService;
    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;



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

    @GetMapping("/order/list")
    public String findAllProducts(Model model) {
        List<ProductDTO> productList = inventoryService.findAllProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }

    @GetMapping("/order/{id}")
    public String findProductById(@PathVariable Long id, Model model) {
        ProductDTO product = inventoryService.findProductById(id);
        model.addAttribute("product", product);
        return "productDetail";
    }

    @GetMapping("/list")
    public String findAllInventory(Model model) {
        List<InventoryVO> inventoryList = inventoryService.findAllInventory();
        model.addAttribute("inventoryList", inventoryList);
        return "/inventory/inventoryList";
    }

    @GetMapping("/{id}")
    public String findInventoryById(@PathVariable Long id, Model model) {
        InventoryVO inventory = inventoryService.findInventoryById(id);
        model.addAttribute("inventory", inventory);
        return "inventoryDetail";
    }

    @GetMapping("/warehouse/{warehouseId}")
    public String findInventoryByWarehouseId(@PathVariable Long warehouseId, Model model) {
        List<InventoryVO> inventoryList = inventoryService.findInventoryByWarehouseId(warehouseId);
        model.addAttribute("inventoryList", inventoryList);
        return "warehouseInventory";
    }

    @GetMapping("/search")
    public String searchInventory(@RequestParam("keyword") String keyword, Model model) {
        List<InventoryVO> inventoryList = inventoryService.search(keyword);
        model.addAttribute("inventoryList", inventoryList);
        return "searchResult";
    }
}