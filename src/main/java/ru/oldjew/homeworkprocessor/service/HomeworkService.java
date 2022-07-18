package ru.oldjew.homeworkprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.oldjew.homeworkprocessor.exceptions.HomeworkNotFoundException;
import ru.oldjew.homeworkprocessor.exceptions.UserNofFoundException;
import ru.oldjew.homeworkprocessor.model.Homework;
import ru.oldjew.homeworkprocessor.model.User;
import ru.oldjew.homeworkprocessor.repository.HomeworkRepository;
import ru.oldjew.homeworkprocessor.repository.UserRepository;

import java.util.List;

@Service
public class HomeworkService {

    private static final Logger logger = LoggerFactory.getLogger(HomeworkService.class);
    private final UserRepository userRepository;
    private final HomeworkRepository homeworkRepository;

    public HomeworkService(UserRepository userRepository, HomeworkRepository homeworkRepository) {
        this.userRepository = userRepository;
        this.homeworkRepository = homeworkRepository;
    }

    public User getUser(Long userId) throws UserNofFoundException {
        return userRepository.findById(userId).orElseThrow(UserNofFoundException::new);
    }

    public List<User> getAllUsers(int page){
        return userRepository.findAll(PageRequest.of(page, 20)).toList();
    }

    public User addUser(String fistName, String lastName){
        return userRepository.save(new User(fistName, lastName));
    }
    public Homework addHomework(Long userId, String title) throws UserNofFoundException {
        User user = getUser(userId);
        Homework homework = new Homework(user, title);
        return homeworkRepository.save(homework);
    }

    public Homework getHomework(Long homeworkId) throws HomeworkNotFoundException {
        return homeworkRepository.findById(homeworkId).orElseThrow(HomeworkNotFoundException::new);
    }

    public List<Homework> getAllHomeworks(int page){
        return homeworkRepository.findAll(PageRequest.of(page, 20)).toList();
    }
    public Homework updateHomeworkStatus(Long homeworkId, Boolean isDone) throws HomeworkNotFoundException {
        Homework homework = getHomework(homeworkId);
        homework.setDone(isDone);
        return homeworkRepository.save(homework);
    }

    public String deleteHomework(Long homeworkId) throws HomeworkNotFoundException{
        Homework homework = getHomework(homeworkId);
        homeworkRepository.delete(homework);
        return "Homework deleted";
    }


}
