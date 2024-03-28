package com.ssg.wsmt.smOrders.controller;

import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
import com.ssg.wsmt.smOrders.dto.PageRequestDTO;
import com.ssg.wsmt.smOrders.dto.PageResponseDTO;
import com.ssg.wsmt.smOrders.dto.SmOrdersDTO;
import com.ssg.wsmt.smOrders.enums.SellerSendStatus;
import com.ssg.wsmt.smOrders.service.SmOrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/smorder")
@Log4j2
@RequiredArgsConstructor
public class SmOrdersController {

    @Autowired
    private final SmOrdersService smOrdersService;

    @GetMapping("/listall")
    public String list(PageRequestDTO pageRequestDTO, Model model,
                       @RequestParam(value = "orderId", required = false) String orderIdStr,
                       @RequestParam(value = "customerId", required = false) String customerIdStr,
                       @RequestParam(value = "status", required = false) String statusStr) {

        Long orderId = null;
        Long customerId = null;
        SellerSendStatus sendStatus = null;

        try {
            orderId = orderIdStr != null && !orderIdStr.isEmpty() ? Long.parseLong(orderIdStr) : null;
            customerId = customerIdStr != null && !customerIdStr.isEmpty() ? Long.parseLong(customerIdStr) : null;
            sendStatus = statusStr != null && !statusStr.isEmpty() ? SellerSendStatus.valueOf(statusStr.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            log.error("Error converting parameters", e);
        }

        PageResponseDTO<SmOrdersDTO> responseDTO;

        if (orderId != null || customerId != null || sendStatus != null) {
            // 검색 조건을 추가하여 검색을 실행합니다.
            List<SmOrdersDTO> list = smOrdersService.searchOrders(orderId, customerId, sendStatus);
            int total = list.size(); // 예시로 리스트 크기를 전체 개수로 사용합니다. 실제 데이터베이스에서 가져와야 합니다.
            responseDTO = PageResponseDTO.<SmOrdersDTO>withAll()
                    .dtoList(list)
                    .page(pageRequestDTO.getPage())
                    .size(pageRequestDTO.getSize())
                    .total(total)
                    .build();
        } else {
            // 검색 조건이 없을 경우, 전체 목록을 조회합니다.
            responseDTO = smOrdersService.ReadAlllist(pageRequestDTO);
        }

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);

        return "smorder/listall";
    }

    // 기본 목록 메소드
    @GetMapping("/listcomplete")
    public String listOrdersComplete(Model model, SmOrdersDTO smOrdersDTO) {
        // 완료건
        model.addAttribute("orders", smOrdersService.readCompleteOrders(smOrdersDTO));
        return "/smorder/listcomplete";
    }

    @GetMapping("/listcancle")
    public String listOrdersCancle(Model model, SmOrdersDTO smOrdersDTO) {
        model.addAttribute("orders", smOrdersService.readAllCanceledOrders());
        log.info(smOrdersService.readAllCanceledOrders());
        return "/smorder/listcancle";
    }

    @GetMapping("/listprepare")
    public String listOrdersPrepare(Model model, SmOrdersDTO smOrdersDTO) {
        model.addAttribute("orders", smOrdersService.readAllPreparedOrders());
        log.info(smOrdersService.readAllPreparedOrders());
        return "/smorder/listprepare";
    }


    @PostMapping("/modify")
    public String modify(@Valid SmOrdersDTO smOrdersDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("id", smOrdersDTO.getId());
            return "redirect:/smorder/modify";
        }
        log.info(smOrdersDTO);

        smOrdersService.updateStatus(smOrdersDTO);
        return "redirect:/smorder/listall";
    }

    @GetMapping("/modify")
    public void modify(Model model, Long orderId2) {
        log.info(smOrdersService.getOne(orderId2));
        log.info(orderId2);

        SmOrdersVo smOrdersVo = smOrdersService.getOne(orderId2);
        log.info(smOrdersVo);

        model.addAttribute("orders", smOrdersVo);
    }

}