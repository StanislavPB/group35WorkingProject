package org.group35workingproject.dto.authDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthRequest {
    private String managerName;
    private String password;

}
