package com.ssg.wsmt.smOrders.controller;
import com.ssg.wsmt.smOrders.domain.SmOrdersVo;
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

@Controller
@RequestMapping("/smorder")
@Log4j2
@RequiredArgsConstructor
public class SmOrdersController {

    @Autowired
    private final SmOrdersService smOrdersService;

    @PostMapping("/modify")
    public String modify(@Valid SmOrdersDTO smOrdersDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            redirectAttributes.addAttribute("id", smOrdersDTO.getId() );
            return "redirect:/smorder/modify";
        }
        log.info(smOrdersDTO);

        smOrdersService.updateStatus(smOrdersDTO);
        return "redirect:/smorder/allorder";
    }


    @GetMapping("/modify")
    public void modify( Model model, Long orderId2) {
        log.info(smOrdersService.getOne( orderId2));
        log.info(orderId2);

        SmOrdersVo smOrdersVo = smOrdersService.getOne(orderId2);
        log.info(smOrdersVo);

        model.addAttribute("orders", smOrdersVo);
    }
    @GetMapping("/allorder")
    public String listOrders(Model model ,SmOrdersDTO smOrdersDTO ) {

        log.info( "~~~~~~~~~~~~~~~~~~~~~~~~~~"+ smOrdersService.findAllSmorders(smOrdersDTO));
        model.addAttribute("orders", smOrdersService.findAllSmorders(smOrdersDTO));
        log.info( "~~~~~~~~~~~~~~~~~~~~~~~~~~"+ smOrdersService.findAllSmorders(smOrdersDTO));
        return "/smorder/allorder";

    }
    @GetMapping("/cancleorder")
    public String listOrdersCancle(Model model ,SmOrdersDTO smOrdersDTO ) {
        model.addAttribute("orders", smOrdersService.readAllCanceledOrders());
        log.info(smOrdersService.readAllCanceledOrders());
        return "/smorder/cancleorder";
    }
    @GetMapping("/prepareorder")
    public String listOrdersPrepare(Model model ,SmOrdersDTO smOrdersDTO ) {
        model.addAttribute("orders", smOrdersService.readAllPreparedOrders());
        log.info(smOrdersService.readAllPreparedOrders());
        return "/smorder/prepareorder";
    }

}