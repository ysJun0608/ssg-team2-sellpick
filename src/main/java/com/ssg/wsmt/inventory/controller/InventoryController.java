package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.domain.InventoryVO;
import com.ssg.wsmt.inventory.service.InventoryService;
import com.ssg.wsmt.product.dto.ProductsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@RequestMapping("/inventory")
@Log4j2
@Controller
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;

    @GetMapping("/order/list")
    public String findAllProducts(Model model) {
        List<ProductsDTO> productList = inventoryService.findAllProducts();
        model.addAttribute("productList", productList);
        return "productList";
    }

    @GetMapping("/order/{id}")
    public String findProductById(@PathVariable Long id, Model model) {
        ProductsDTO product = inventoryService.findProductById(id);
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

   