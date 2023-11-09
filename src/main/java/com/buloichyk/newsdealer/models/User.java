package com.buloichyk.newsdealer.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "person")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Username field can not be empty.")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters.")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "This field is required.")
    @Size(min = 8, message = "Password must have at least 8 symbols.")
    @Column(name = "password")
    private String password;

    @Past(message = "Incorrect date of birthday chosen.")
    @Column(name = "date_of_birthday")
    private LocalDate dateOfBirthday;

    @NotNull(message = "Choose country from the list.")
    @Column(name = "country")
    private String country;

    @NotNull(message = "Choose language from the list.")
    @Column(name = "language")
    private String language;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "person_category",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
