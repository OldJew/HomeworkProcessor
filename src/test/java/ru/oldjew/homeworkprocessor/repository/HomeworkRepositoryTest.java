package ru.oldjew.homeworkprocessor.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.oldjew.homeworkprocessor.model.Homework;
import ru.oldjew.homeworkprocessor.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class HomeworkRepositoryTest {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Test
    void ifAddHomeworkShouldAddHomework(){
        User user = new User(2222L, "FirstName", "LastName"); //from test-data.sql

        Homework homework = new Homework( 2l, user, "Test");
        homeworkRepository.save(homework);
        Optional<Homework> result = homeworkRepository.findById(2l);
        assertTrue(result.isPresent());

    }
}