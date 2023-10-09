package com.buloichyk.newsdealer.controllers;

import com.buloichyk.newsdealer.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "auth_page";
    }

    @GetMapping("/hello")
    public String test() {
        return "hello";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userDTO")UserDTO userDTO) {
        return "registration_form";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userDTO")UserDTO userDTO) {
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
        System.out.println(userDTO.getEmail());
        System.out.println(userDTO.getDateOfBirthday());
        System.out.println(userDTO.getCountry());
        return "redirect:/main";
    }
}
