package com.ssg.wsmt.member.controller;


import com.ssg.wsmt.member.dto.UserDTO;
import com.ssg.wsmt.member.exception.DuplicateUserException;
import com.ssg.wsmt.member.service.JoinService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequestMapping("/login")
@RequiredArgsConstructor
public class UserController {

    private final JoinService joinService;

    //로그인 페이지 라우팅,에러 시 에러 처리한 문구 실행
    @GetMapping("/login")
    public String login(@RequestParam(value ="expired" ,required = false) String expired,
                        @RequestParam(value = "error",required = false) String error, @RequestParam(value = "exception",required = false)String exception, Model model){
        if("true".equals("expired")){
            model.addAttribute("message", "다른 곳에서 접근되었습니다, 연결을 종료합니다");
        }
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "login/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
    //회원 가입 페이지 라우팅
    @GetMapping("/join")
    public String join() {
        log.info("UserController join");
        return "login/join";
    }


    //회원 가입 processing 이메일이 중복된 경우 이미 존재하는 회원 반환
    @PostMapping(value = "/joinProc", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> joinProcess(@ModelAttribute UserDTO userDTO) {
        log.info(userDTO.getUsername());
        try {
            joinService.joinProcess(userDTO);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        } catch (DuplicateUserException d) {
            return ResponseEntity.badRequest().body("이미 존재하는 회원입니다.");
        }
    }
}

