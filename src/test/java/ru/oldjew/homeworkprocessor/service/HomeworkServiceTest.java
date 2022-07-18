package ru.oldjew.homeworkprocessor.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import ru.oldjew.homeworkprocessor.exceptions.HomeworkNotFoundException;
import ru.oldjew.homeworkprocessor.exceptions.UserNofFoundException;
import ru.oldjew.homeworkprocessor.model.Homework;
import ru.oldjew.homeworkprocessor.model.User;
import ru.oldjew.homeworkprocessor.repository.HomeworkRepository;
import ru.oldjew.homeworkprocessor.repository.UserRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeworkServiceTest {

    @InjectMocks
    private HomeworkService homeworkService;
    @Mock
    private HomeworkRepository homeworkRepository;
    @Mock
    private UserRepository userRepository;

    @DisplayName("GET_USER_TEST")
    @Test
    void getUser() throws UserNofFoundException {
        Mockito.doReturn(
                Optional.of(new User(3000l, "FirstName", "LastName")))
                .when(userRepository).findById(3000l);
        User result = homeworkService.getUser(3000L);
        assertEquals(3000L, result.getId());
        assertEquals("FirstName", result.getFirstName());
        assertEquals("LastName", result.getLastName());
    }

    //get users on first page
    @DisplayName("GET_ALL_USERS_TEST")
    @Test
    void getAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        Page<User> usersPage = new PageImpl<>(userList);
        Mockito.doReturn(
                    usersPage)
                    .when(userRepository).findAll(PageRequest.of(0,20));
        List<User> result = homeworkService.getAllUsers(0);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().count() == 2); //2 users from test-data.sql
    }

    @DisplayName("ADD_USER_TEST")
    @Test
    void addUser() throws UserNofFoundException {
        Mockito.doReturn(
                new User("Post", "User"))
                .when(userRepository).save(new User("Post", "User"));
        User result = homeworkService.addUser("Post", "User");

        Mockito.doReturn(
                 Optional.of(new User("Post", "User")))
                .when(userRepository).findById(result.getId());
        User userFromDB = homeworkService.getUser(result.getId());
        assertTrue(userFromDB != null);
        assertEquals(result.getId(), userFromDB.getId());
    }

    @DisplayName("ADD_HOMEWORK_TEST")
    @Test
    void addHomework() throws UserNofFoundException {
        Mockito.doReturn(
                        Optional.of(new User(3003l,"Test", "User")))
                .when(userRepository).findById(3003l);
        User user = userRepository.findById(3003l).get();
        Mockito.doReturn(
                        new Homework(user,"Title for test"))
                .when(homeworkRepository).save(new Homework(user, "Title for test"));
        Homework result = homeworkService.addHomework(3003l, "Title for test");
        assertEquals("Title for test", result.getTitle());
    }

    @DisplayName("GET_HOMEWORK_TEST")
    @Test
    void getHomework() throws HomeworkNotFoundException {
        Mockito.doReturn(
                        Optional.of(new Homework(1l, new User("Test", "Test"), "Test homework")))
                .when(homeworkRepository).findById(1l);
        Homework result = homeworkService.getHomework(1L);
        assertEquals("Test homework", result.getTitle());
    }

    @DisplayName("GET_ALL_HOMEWORKS_TEST")
    @Test
    void getAllHomeworks() {
        List<Homework> homeworkList = new ArrayList<>();
        homeworkList.add(new Homework());
        homeworkList.add(new Homework());
        Page<Homework> homeworksPage = new PageImpl<>(homeworkList);
        Mockito.doReturn(
                       homeworksPage)
                .when(homeworkRepository).findAll(PageRequest.of(0,20));
        List<Homework> result = homeworkService.getAllHomeworks(0);
        assertFalse(result.isEmpty());
        assertTrue(result.stream().count() == 2); //1 hw from test-data.sql and 1 from addHomework method
    }

    @DisplayName("UPDATE_HOMEWORK_TEST")
    @Test
    void updateHomeworkStatus() throws HomeworkNotFoundException {
        Mockito.doReturn(
                        Optional.of(new Homework(4444l,new User(), "Title")))
                .when(homeworkRepository).findById(4444l);
        Mockito.doReturn(
                        new Homework(4444l,new User(), "Title", true))
                .when(homeworkRepository).save(new Homework(4444l, new User(), "Title", true));
        Homework result = homeworkService.updateHomeworkStatus(4444L, true);
        assertEquals(true, result.isDone());
    }

    @DisplayName("DELETE_HOMEWORK")
    @Test
    void deleteHomework() throws HomeworkNotFoundException {
        Mockito.doReturn(
                        Optional.of(new Homework(111l,new User(), "Title")))
                .when(homeworkRepository).findById(111l);
        String result = homeworkService.deleteHomework(111l);
        assertEquals("Homework deleted", result);
    }
}