package com.buloichyk.newsdealer.repositories;

import com.buloichyk.newsdealer.models.Category;
import com.buloichyk.newsdealer.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setupTestData(){
        // Given : Setup object or precondition
        user = new User();
        user.setUsername("TestPerson");
        user.setPassword("TestPassword");
        user.setCountry("Belarus");
        user.setLanguage("English");
        user.setDateOfBirthday(LocalDate.of(2000, 3, 31));
        Category category1 = new Category();
        category1.setName("World");
        Category category2 = new Category();
        category2.setName("Top");
        Category category3 = new Category();
        category3.setName("Business");
        user.setCategories(new ArrayList<Category>(List.of(category1, category2, category3)));
    }

    @Test
    @DisplayName("Trying to save the object")
    public void givenUserObject_whenSave_thenSaved() {
        User saveUser = userRepository.save(user);

        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getId()).isGreaterThan(0);
    }
}
