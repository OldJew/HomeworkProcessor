package ru.oldjew.homeworkprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oldjew.homeworkprocessor.model.Homework;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

}
