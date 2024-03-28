package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.SettlementDTO;
import com.ssg.wsmt.inventory.service.SettlementService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/settlement")
@Log4j2
@RequiredArgsConstructor

public class SettlementController {
    @Autowired
    private final SettlementService settlementService;

//    @Autowired // SettlementService 인스턴스를 주입받기 위한 어노테이션
//    public SettlementController(SettlementService settlementService) {
//        this.settlementService = settlementService;
//    }

    // 리스트
    @GetMapping("/list")
    public String list(Model model){
        List<SettlementDTO> settlementList = settlementService.settlementList();
        model.addAttribute("dtoList",settlementList);
        log.info(settlementList);
        return "settlement/list";
    }
}
