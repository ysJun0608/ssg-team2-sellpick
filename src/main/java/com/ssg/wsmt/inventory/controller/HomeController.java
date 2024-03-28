package com.ssg.wsmt.inventory.controller;


import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.service.WarehouseInsertReleaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final WarehouseInsertReleaseService warehouseInsertReleaseService;

    @GetMapping({"", "/home"})
    public void goHome(Model model, @Valid WarehouseInsertReleaseDTO warehouseInsertReleaseDTO) {
        Long TodayCost = warehouseInsertReleaseService.findTodayInsertCost(warehouseInsertReleaseDTO);
        Long TodayPrice = warehouseInsertReleaseService.findTodayReleasePrice(warehouseInsertReleaseDTO);
        Long TodayRevenue = warehouseInsertReleaseService.findTodayRevenue(warehouseInsertReleaseDTO);
        model.addAttribute("TodayCost", TodayCost);
        model.addAttribute("TodayPrice", TodayPrice);
        model.addAttribute("TodayRevenue", TodayRevenue);

        log.info("goHome");
    }
}