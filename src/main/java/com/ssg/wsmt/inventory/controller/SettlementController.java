package com.ssg.wsmt.inventory.controller;

import com.ssg.wsmt.inventory.dto.PageRequestDTO;
import com.ssg.wsmt.inventory.dto.PageResponseDTO;
import com.ssg.wsmt.inventory.dto.SettlementDTO;
import com.ssg.wsmt.inventory.service.SettlementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/settlement")
@Log4j2
@RequiredArgsConstructor

public class SettlementController {
    private final SettlementService settlementService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model,
                       @RequestParam(value = "settlementId", required = false) String settlementIdStr) {

        Long settlementId = null;


        try {
            settlementId = settlementIdStr != null && !settlementIdStr.isEmpty() ? Long.parseLong(settlementIdStr) : null;
        } catch (IllegalArgumentException e) {
            log.error("Error converting parameters", e);
        }

        PageResponseDTO<SettlementDTO> responseDTO;

        if (settlementId != null) {
            // 검색 조건을 추가하여 검색을 실행합니다.
            List<SettlementDTO> list = settlementService.searchSet(settlementId);
            int total = list.size(); // 예시로 리스트 크기를 전체 개수로 사용합니다. 실제 데이터베이스에서 가져와야 합니다.
            responseDTO = PageResponseDTO.<SettlementDTO>withAll()
                    .dtoList(list)
                    .pageRequestDTO(pageRequestDTO)
//                    .page(pageRequestDTO.getPage())
//                    .size(pageRequestDTO.getSize())
                    .total(total)
                    .build();
        } else {
            // 검색 조건이 없을 경우, 전체 목록을 조회합니다.
            responseDTO = settlementService.ReadAllSetlist(pageRequestDTO);
        }

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("dtoList", responseDTO.getDtoList());
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "settlement/list";
    }


    // 기간별 정산 리스트
    @PostMapping("/list")
    public String setDateList(
            @RequestParam("startyear") int startYear,
            @RequestParam("startmonth") int startMonth,
            @RequestParam("startday") int startDay,
            @RequestParam("endyear") int endYear,
            @RequestParam("endmonth") int endMonth,
            @RequestParam("endday") int endDay,
            Model model) {

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
        List<SettlementDTO> dateSetList = settlementService.setDateList(startDate, endDate);
        model.addAttribute("dtoList", dateSetList); // 이름을 "dateSetList"에서 "dtoList"로 변경
        return "settlement/list";
    }

}