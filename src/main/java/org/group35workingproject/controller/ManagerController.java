package org.group35workingproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.group35workingproject.dto.managerDto.ManagerCreateRequestDTO;
import org.group35workingproject.dto.managerDto.ManagerCreateResponseDTO;
import org.group35workingproject.dto.taskDto.TaskCreateOrUpdateResponseDTO;
import org.group35workingproject.dto.taskDto.TaskCreateRequestDTO;
import org.group35workingproject.dto.taskDto.TaskResponseDTO;
import org.group35workingproject.service.ManagerService;
import org.group35workingproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/managers")
public class ManagerController {

    private final TaskService taskService;


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
