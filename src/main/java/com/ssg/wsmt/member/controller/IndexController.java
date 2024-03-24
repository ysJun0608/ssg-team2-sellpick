package com.ssg.wsmt.member.controller;

import groovy.util.logging.Log4j2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

@Controller
public class IndexController {

    @GetMapping({"", "/"})
    public String indexPage(Model model) {

        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("id", id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority authority = iter.next();
        String role = authority.getAuthority();

        model.addAttribute("role", role);
        return "index";
    }


}

