package org.group35workingproject.dto.managerDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ManagerCreateResponseDTO {

    private Integer id;
    private String managerName;
    private String roleName;

}
