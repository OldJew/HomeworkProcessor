package ru.oldjew.homeworkprocessor.exceptions;

public class UserNofFoundException extends Exception{

    @Override
    public String getMessage() {
        return "User not found";
    }

}
