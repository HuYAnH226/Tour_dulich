package com.example.TourPrjPtit_2025.controller;

import com.example.TourPrjPtit_2025.entity.User;
import com.example.TourPrjPtit_2025.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody Map<String, String> body) {
        return userService.register(body.get("username"), body.get("password"));
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        boolean success = userService.login(body.get("username"), body.get("password"));
        return success ? "Login success!" : "Invalid username or password";
    }
}