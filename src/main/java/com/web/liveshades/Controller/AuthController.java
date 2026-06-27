package com.web.liveshades.Controller;

import com.web.liveshades.DTO.LoginRequestDTO;
import com.web.liveshades.DTO.LoginResponceDTO;
import com.web.liveshades.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponceDTO login(@RequestBody LoginRequestDTO request) {
        return userService.login(request);
    }
}