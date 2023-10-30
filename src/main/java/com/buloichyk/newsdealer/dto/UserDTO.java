package com.buloichyk.newsdealer.dto;


import com.buloichyk.newsdealer.models.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    @NotEmpty(message = "Username field can not be empty.")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters.")
    private String username;
    @NotEmpty(message = "This field is required.")
    @Size(min = 8, message = "Password must have at least 8 symbols.")
    private String password;

    @Past(message = "Incorrect date of birthday chosen.")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // fix incorrect passing format to profile
    private LocalDate dateOfBirthday;

    @NotNull(message = "Choose country from the list.")
    private String country;

    @NotNull(message = "Choose language from the list.")
    private String language;

    private List<Category> categories;

    private List<Integer> selectedCategoriesIds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(LocalDate dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<Integer> getSelectedCategoriesIds() {
        return selectedCategoriesIds;
    }

    public void setSelectedCategoriesIds(List<Integer> selectedCategoriesIds) {
        this.selectedCategoriesIds = selectedCategoriesIds;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Integer> getCategoriesIds() {
        List<Integer> categoriesIds = new ArrayList<>();
        for (Category category: getCategories()) {
            categoriesIds.add(category.getId());
        }
        return categoriesIds;
    }
}
