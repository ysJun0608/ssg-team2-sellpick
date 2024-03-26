package com.ssg.wsmt.member.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;



public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMessage;

        if (exception instanceof BadCredentialsException) {
            errorMessage = ErrorCode.ID_OR_PASSWORD_FAIL.getDescription();
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = ErrorCode.USER_NOT_FOUND_BY_SYSTEM_ERROR.getDescription();
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = ErrorCode.USER_NOT_FOUND.getDescription();
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = ErrorCode.USER_NOT_AUTHENTICATE.getDescription();
        } else {
            errorMessage = ErrorCode.UNKNOWN_ERROR.getDescription();
        }

        String redirectUrl = "/login/login?error=true&exception=" + errorMessage;
        response.sendRedirect(redirectUrl);

        // JavaScript 함수 호출
        String jsCode = "<script>showErrorAlert('" + errorMessage + "');</script>";
        response.getWriter().write(jsCode);

    }
}

