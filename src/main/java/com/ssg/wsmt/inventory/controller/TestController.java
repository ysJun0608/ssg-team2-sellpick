package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.WarehouseDTO;
import com.ssg.wsmt.inventory.service.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Log4j2
@Controller
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class TestController {
    private final WarehouseService warehouseService;

//    @RequestMapping(value = "/warehouselist2", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<PageResponseDTO<WarehouseDTO>> warehouseList(@Valid PageRequestDTO pageRequestDTO) {
//        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.readAllWarehouseWithSection(pageRequestDTO);
//        return ResponseEntity.ok(responseDTO);
//    }



//@GetMapping("/warehouselist2")
//public void warehouseCreate(@Valid PageRequestDTO pageRequestDTO, Model model) {
//    PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.readAllWarehouseWithSection(pageRequestDTO);
//    model.addAttribute("responseDTO", responseDTO);
//    model.addAttribute("pageRequestDTO", pageRequestDTO);
//    log.info("warehouseList SUCESS");
//    log.info(responseDTO.getDtoList());
//    log.info(pageRequestDTO.toString());
//}

    @RequestMapping(value = "/warehouselist2", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PageResponseDTO<WarehouseDTO>> warehouseList(@Valid PageRequestDTO pageRequestDTO) {
        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.readAllWarehouseWithSection(pageRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

}