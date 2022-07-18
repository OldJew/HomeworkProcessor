package ru.oldjew.homeworkprocessor.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NonNull;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseJson {

    @NonNull
    private final int value;

    private Object result;

    public ResponseJson(@NonNull int value, Object result) {
        this.value = value;
        this.result = result;
    }
}







