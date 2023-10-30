package com.buloichyk.newsdealer.controllers;

import com.buloichyk.newsdealer.dto.CategoryDTO;
import com.buloichyk.newsdealer.dto.UserDTO;
import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.models.User;
import com.buloichyk.newsdealer.security.UserDetailsImpl;
import com.buloichyk.newsdealer.services.CategoryService;
import com.buloichyk.newsdealer.services.NewsGeneratorService;
import com.buloichyk.newsdealer.services.RegistrationService;
import com.buloichyk.newsdealer.services.UserService;
import com.buloichyk.newsdealer.util.SearchObject;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final RegistrationService registrationService;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    private final NewsGeneratorService newsGeneratorService;
    private final UserService userService;

    @Autowired
    public UserController(RegistrationService registrationService, CategoryService categoryService, ModelMapper modelMapper, NewsGeneratorService newsGeneratorService, UserService userService) {
        this.registrationService = registrationService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.newsGeneratorService = newsGeneratorService;
        this.userService = userService;
    }

    @GetMapping("/main")
    public String mainPage(Model model, @ModelAttribute("searchObject")SearchObject searchObject) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // checking here is user authenticated or anonymous
        if (authentication instanceof AnonymousAuthenticationToken) {
            model.addAttribute("allNews", newsGeneratorService.generateRandomNews(searchObject));
        } else {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User authorizedUser = userDetails.getUser();
            User user = userService.getUserById(authorizedUser.getId());
            model.addAttribute("allNews", newsGeneratorService.generateNews(user, searchObject));
        }
        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("userDTO")UserDTO userDTO, Model model) {
        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories().stream().map(this::convertToCategoryDTO).toList();
        model.addAttribute("categoriesDTO", categoriesDTO);
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<CategoryDTO> categoriesDTO = categoryService.getAllCategories().stream().map(this::convertToCategoryDTO).toList();
            model.addAttribute("categoriesDTO", categoriesDTO);
            return "registration";
        }
        List<Category> selectedCategories = userDTO.getSelectedCategoriesIds().stream()
                .map(categoryService::getById).toList();
        User user = convertToUser(userDTO);
        user.setCategories(selectedCategories);
        registrationService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User authorizedUser = userDetails.getUser();
        // get actual data about user
        User user = userService.getUserById(authorizedUser.getId());
        UserDTO userDTO = convertToUserDTO(user);
        userDTO.getCategories().forEach(e -> System.out.println(e.getName()));
        model.addAttribute("userDTO", userDTO);
        List<CategoryDTO> categoriesDTO = categoryService.getAllCategories().stream().map(this::convertToCategoryDTO).toList();
        model.addAttribute("categoriesDTO", categoriesDTO);
        return "profile";
    }

    @PatchMapping ("edit")
    public String edit(@ModelAttribute("userDTO") UserDTO userDTO, Model model) {
//        if (bindingResult.hasErrors()) {
//            List<CategoryDTO> categoriesDTO = categoryService.getAllCategories().stream().map(this::convertToCategoryDTO).toList();
//            model.addAttribute("categoriesDTO", categoriesDTO);
//            return "profile";
//        }
        List<Category> selectedCategories = userDTO.getSelectedCategoriesIds().stream()
                .map(categoryService::getById).toList();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User authorizedUser = userDetails.getUser();
        User user = convertToUser(userDTO);
        user.setId(authorizedUser.getId());
        user.setPassword(authorizedUser.getPassword());
        user.setCategories(selectedCategories);
        userService.update(user);
        return "redirect:/main";
    }


    private User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    private CategoryDTO convertToCategoryDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
