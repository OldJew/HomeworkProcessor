package ru.oldjew.homeworkprocessor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.oldjew.homeworkprocessor.exceptions.HomeworkNotFoundException;
import ru.oldjew.homeworkprocessor.exceptions.UserNofFoundException;
import ru.oldjew.homeworkprocessor.model.Homework;
import ru.oldjew.homeworkprocessor.model.ResponseJson;
import ru.oldjew.homeworkprocessor.model.User;
import ru.oldjew.homeworkprocessor.service.HomeworkService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeworkController {

    private final HomeworkService homeworkService;

    @Operation(summary = "Get user by Id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "found user",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)

    })
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser(@RequestParam(value = "id")Long userId) throws UserNofFoundException {
            User user = homeworkService.getUser(userId);
            return ResponseEntity.ok(user);
    }

    @Operation(summary = "Get Users on page")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "found users",
                    content = { @Content(mediaType = "application/json",
                            array= @ArraySchema(schema =@Schema(implementation = User.class))) })

    })
    @GetMapping("/users/{page}")
    public ResponseJson getAllUsers(@PathVariable int page){
        List<User> result = homeworkService.getAllUsers(page);
        if (!result.isEmpty()){
            return new ResponseJson(1, result);
        } else {
            return new ResponseJson(-1, "Нет такой страницы");
        }
    }

    @Operation(summary = "add User")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "user added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class)) })

    })
    @PostMapping("/addUser")
    public ResponseJson addUser(@RequestParam(value = "firstName") String fistName,
                                @RequestParam(value = "lastName") String lastName){
        User user = homeworkService.addUser(fistName, lastName);
        return new ResponseJson(1, user);
    }
    @Operation(summary = "add Homework")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "homework added",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class)) })

    })
    @PostMapping("/addHomework")
    public ResponseJson addHomework(@RequestParam(value = "userId") long userId,
                                    @RequestParam(value = "title") String title){
        ResponseJson responseJson;
        try {
            responseJson = new ResponseJson(1, homeworkService.addHomework(userId, title));
        } catch (Exception e) {
            responseJson = new ResponseJson(-1, "Пользователь не найден");
        }
        return responseJson;
    }

    @Operation(summary = "Get homework by Id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "get homework",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Homework.class)) }),
            @ApiResponse(responseCode = "404", description = "Homework not found",
                    content = @Content)

    })
    @GetMapping("/getHomework")
    public ResponseEntity<Homework> getHomework(@RequestParam(value = "id")Long homeworkId)
            throws HomeworkNotFoundException {

        return ResponseEntity.ok(homeworkService.getHomework(homeworkId));
    }

    @Operation(summary = "Get Homeworks on page")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "found homeworks",
                    content = { @Content(mediaType = "application/json",
                            array= @ArraySchema(schema =@Schema(implementation = Homework.class))) })

    })
    @GetMapping("/homeworks/{page}")
    public ResponseJson getAllHomeworks(@PathVariable int page){
        List<Homework> result = homeworkService.getAllHomeworks(page);
        if (!result.isEmpty()){
            return new ResponseJson(1, result);
        } else {
            return new ResponseJson(-1, "Нет такой страницы");
        }
    }

    @Operation(summary = "Update homework by Id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Homework updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Homework.class)) }),
            @ApiResponse(responseCode = "404", description = "Homework not found",
                    content = @Content)

    })
    @PutMapping("/updateHomework")
    public ResponseEntity<Homework> updateHomework(@RequestParam(value = "id") long homeworkId,
                                                   @RequestParam(value = "status") boolean isDone)
                                                                        throws HomeworkNotFoundException {
        Homework homework = homeworkService.updateHomeworkStatus(homeworkId, isDone);
        return ResponseEntity.ok(homework);
    }

    @Operation(summary = "Delete homework by Id")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Homework deleted",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class)) }),
            @ApiResponse(responseCode = "404", description = "Homework not found",
                    content = @Content)

    })
    @DeleteMapping("/deleteHomework")
    public ResponseEntity<ResponseJson> deleteHomework(@RequestParam(value = "id") long homeworkId)
                                                                    throws HomeworkNotFoundException {
        ResponseJson responseJson = new ResponseJson(1, homeworkService.deleteHomework(homeworkId));
        return ResponseEntity.ok(responseJson);
    }
}
