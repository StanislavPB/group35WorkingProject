package org.group35workingproject.service;

import lombok.AllArgsConstructor;
import org.group35workingproject.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;


   // public List<> findAll(){}
}
