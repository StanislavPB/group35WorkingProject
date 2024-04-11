package org.group35workingproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.group35workingproject.dto.taskDto.TaskCreateOrUpdateResponseDTO;
import org.group35workingproject.dto.taskDto.TaskCreateRequestDTO;
import org.group35workingproject.dto.taskDto.TaskResponseDTO;
import org.group35workingproject.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get a list of all tasks")
    @ApiResponse(responseCode = "200", description = "Successfully retried lit",
    content = { @Content(mediaType = "application/json",
    schema = @Schema(implementation = TaskResponseDTO.class))})
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks(){
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    };

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(taskService.findById(id), HttpStatus.OK);
    };


    @GetMapping(params = "managerName")
    public ResponseEntity<List<TaskResponseDTO>> findTaskByManagerName(@RequestParam String managerName){
        return new ResponseEntity<>(taskService.findTasksByManagerName(managerName), HttpStatus.OK);
    };


    @PostMapping
    @Operation(summary = "Create a new task", description = "Создание новой задачи из данных, полученных от пользователя")
    @ApiResponse(responseCode = "201", description = "Task created",
    content = {
            @Content(mediaType = "application/json",
            schema = @Schema(implementation = TaskCreateOrUpdateResponseDTO.class))
    })
    public ResponseEntity<TaskCreateOrUpdateResponseDTO> createNewTask(@RequestBody TaskCreateRequestDTO request){
        return new ResponseEntity<>(taskService.createTask(request), HttpStatus.CREATED);
    };


}
