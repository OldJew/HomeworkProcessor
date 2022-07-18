package ru.oldjew.homeworkprocessor.exceptions;

public class HomeworkNotFoundException extends Exception{

    @Override
    public String getMessage() {
        return "Homework not found";
    }
}
