package com.ssg.wsmt.member.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.Iterator;

@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String indexPage() {

        return "home";
    }

    @GetMapping("/2")
    public String indexPage2(Model model) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("id", id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority authority = iter.next();
        String role = authority.getAuthority();

        model.addAttribute("role", role);
        return "example";
    }


    @GetMapping({"test"})
    public String testPage() {

        return "example";
    }


}

