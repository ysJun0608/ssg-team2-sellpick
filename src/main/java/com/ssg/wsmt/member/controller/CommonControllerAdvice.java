package com.ssg.wsmt.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


//이 클래스는 모든 컨트롤러에서 매핑되기전 고정된 값인 사이드바나 navbar에 사용자의 정보를 불러오기 위해 AOP 사용
@ControllerAdvice
public class CommonControllerAdvice {
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String role = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("id", id);
        model.addAttribute("role", role);
    }


}
