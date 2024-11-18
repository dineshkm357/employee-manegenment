package com.example.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.service.LoginService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private LoginService loginService;

    public LoginController() {
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String userName, HttpServletResponse request) {
        return loginService.loginUser(userName, request);
    }

    @PostMapping("/logout")
    public String logoutUser(@RequestParam String userName) {
        return loginService.logoutUser(userName);
    }
}
