package ru.oldjew.homeworkprocessor.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import ru.oldjew.homeworkprocessor.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    void ifAddUserShouldAddUser(){
        final User user = new User(100L, "FirstName", "LastName");
        userRepository.save(user);
        Optional<User> userFromDB = userRepository.findById(100L);
        assertTrue(userFromDB.isPresent());
    }
}