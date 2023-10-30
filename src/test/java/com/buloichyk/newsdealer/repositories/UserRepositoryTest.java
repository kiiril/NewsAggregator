package com.buloichyk.newsdealer.repositories;

import com.buloichyk.newsdealer.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setupTestData(){
        // Given : Setup object or precondition

    }

    @Test
    public void whenFindByUsername_thenOptionalUser() {
        String username = "kiiril";
        Optional<User> user = userRepository.findByUsername(username);
    }
}
