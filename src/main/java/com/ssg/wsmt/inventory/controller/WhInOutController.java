package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.dto.WhPageRequestDTO;
import com.ssg.wsmt.inventory.dto.WhPageResponseDTO;
import com.ssg.wsmt.inventory.service.WarehouseInsertReleaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/inventory/whInOut")
@RequiredArgsConstructor
public class WhInOutController {
    private final WarehouseInsertReleaseService warehouseInsertReleaseService;

    @GetMapping("/list/{warehouseId}")
    public String list(@PathVariable Long warehouseId, @Valid WhPageRequestDTO whPageRequestDTO, Model model) {
        log.info("WhInOutController list");
        log.info("warehouseId: " + warehouseId);
        log.info("whPageRequestDTO: " + whPageRequestDTO);
        WhPageResponseDTO<WarehouseInsertReleaseDTO> list = warehouseInsertReleaseService.findAllInsertReleaseList(warehouseId, whPageRequestDTO);
        model.addAttribute("responseDTO", list);
        model.addAttribute("whPageRequestDTO", whPageRequestDTO);

        return "whInOut/list";
    }
}
