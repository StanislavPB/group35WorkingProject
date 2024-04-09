package org.group35workingproject.dto.managerDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.group35workingproject.domain.Role;

@Data
@AllArgsConstructor
public class ManagerResponseDTO {

    private Integer id;
    private String managerName;
    private String email;
    private Role role;
}
