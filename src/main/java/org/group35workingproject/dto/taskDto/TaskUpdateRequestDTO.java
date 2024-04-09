package org.group35workingproject.dto.taskDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.group35workingproject.domain.TaskStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskUpdateRequestDTO {

    private Integer id;
    private String taskName;
    private String description;
    private LocalDateTime deadline;
    private TaskStatus status;
    private String managerName;
}
