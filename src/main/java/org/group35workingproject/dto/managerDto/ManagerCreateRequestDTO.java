package org.group35workingproject.dto.managerDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerCreateRequestDTO {

    private String managerName;
    private String password;
    private String email;
}
