package com.buloichyk.newsdealer.controllers;

import com.buloichyk.newsdealer.dto.UserDTO;
import com.buloichyk.newsdealer.models.ResponseObject;
import com.buloichyk.newsdealer.models.User;
import com.buloichyk.newsdealer.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        // TODO all the logic must be in service, not controller
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://newsdata.io/api/1/news?apikey=pub_30680664b8e369f58dc8cbbeebd4540e5069a&category=politics&language=en";
        ResponseObject responseObject = restTemplate.getForObject(url, ResponseObject.class);
        model.addAttribute("allNews", responseObject);
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
        System.out.println(userDTO.getDateOfBirthday());
        System.out.println(userDTO.getCountry());
        registrationService.register(convertToUser(userDTO));
        return "redirect:/login";
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}
