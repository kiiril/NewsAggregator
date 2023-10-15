package com.buloichyk.newsdealer.dto;


import java.time.LocalDate;
import java.util.List;

public class UserDTO {
    private String username;
    private String password;

    private LocalDate dateOfBirthday;

    private String country;

    private String language;

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
}
