package com.buloichyk.newsdealer.controllers;

import com.buloichyk.newsdealer.dto.CategoryDTO;
import com.buloichyk.newsdealer.dto.UserDTO;
import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.models.User;
import com.buloichyk.newsdealer.security.UserDetailsImpl;
import com.buloichyk.newsdealer.services.CategoryService;
import com.buloichyk.newsdealer.services.NewsGeneratorService;
import com.buloichyk.newsdealer.services.RegistrationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final RegistrationService registrationService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    private final NewsGeneratorService newsGeneratorService;

    @Autowired
    public UserController(RegistrationService registrationService, CategoryService categoryService, ModelMapper modelMapper, NewsGeneratorService newsGeneratorService) {
        this.registrationService = registrationService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.newsGeneratorService = newsGeneratorService;
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // checking here is user authenticated or anonymous one
        if (authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("allNews", newsGeneratorService.generateRandomNews());
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User authorizedUser = userDetails.getUser();
            model.addAttribute("allNews", newsGeneratorService.generateNews(authorizedUser));
        }
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
    public String registrationPage(@ModelAttribute("userDTO")UserDTO userDTO, Model model) {
        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories().stream().map(this::convertToCategoryDTO).toList();
        model.addAttribute("categoriesDTO", categoriesDTO);
        return "registration_form";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userDTO")UserDTO userDTO) {
        List<Category> selectedCategories = userDTO.getSelectedCategoriesIds().stream()
                .map(categoryService::getById).toList();
        User user = convertToUser(userDTO);
        user.setCategories(selectedCategories);
        registrationService.register(user);
        return "redirect:/login";
    }

    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
