package org.group35workingproject.dto.taskDto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TaskCreateRequestDto {

    private String taskName;
    private String description;
    private LocalDateTime deadline;
    private String managerName;

}
