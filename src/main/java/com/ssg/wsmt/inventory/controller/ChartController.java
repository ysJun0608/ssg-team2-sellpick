package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.InvenInOutChartDTO;
import com.ssg.wsmt.inventory.dto.SettlementChartDTO;
import com.ssg.wsmt.inventory.dto.WarehouseInsertReleaseDTO;
import com.ssg.wsmt.inventory.service.SettlementService;
import com.ssg.wsmt.inventory.service.WarehouseInsertReleaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/chart")
@RequiredArgsConstructor
public class ChartController {

    private final WarehouseInsertReleaseService warehouseInsertReleaseService;
    private final SettlementService settlementService;

    @GetMapping("/chart-data")
    @ResponseBody
    public List<InvenInOutChartDTO> getSalesData() {
        // 판매 데이터 가져오기
        return warehouseInsertReleaseService.getAllSales();
    }

    @GetMapping("/settlement-data")
    @ResponseBody
    public List<SettlementChartDTO> getSettlementData() {
        // 정산 데이터 가져오기
        return settlementService.getChartData();
    }

    @GetMapping("/whInOut/recent")
    @ResponseBody
    public List<WarehouseInsertReleaseDTO> recent() {
        return warehouseInsertReleaseService.recentInsertReleaseList();
    }
}
