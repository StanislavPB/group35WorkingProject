package org.group35workingproject.repository;


import org.group35workingproject.domain.Manager;
import org.group35workingproject.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByTaskName(String taskName);

    List<Task> findByManager(Manager manager);
}
