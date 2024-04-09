package org.group35workingproject.service;

import lombok.AllArgsConstructor;
import org.group35workingproject.domain.Task;
import org.group35workingproject.dto.taskDto.TaskResponseDTO;
import org.group35workingproject.repository.TaskRepository;
import org.group35workingproject.service.exception.NotFoundException;
import org.group35workingproject.service.util.TaskConverter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskConverter converter;

    public List<TaskResponseDTO> findAll(){
//        List<Task> tasks = taskRepository.findAll();
//        List<TaskResponseDTO> dtos = new ArrayList<>();
//        for (Task task : tasks) {
//            TaskResponseDTO dto = converter.toDto(task);
//            dtos.add(dto);
//        }
//
//        return dtos;

        return taskRepository.findAll().stream()
                .map(converter::toDto)
                .toList();
    }

    public TaskResponseDTO findById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id = " + id + " not found"));
        return converter.toDto(task);
    }
}
