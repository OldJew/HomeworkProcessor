package ru.oldjew.homeworkprocessor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.oldjew.homeworkprocessor.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}