package org.group35workingproject.service.util;

import org.group35workingproject.domain.Manager;
import org.group35workingproject.domain.Role;
import org.group35workingproject.dto.managerDto.ManagerCreateRequestDTO;
import org.group35workingproject.dto.managerDto.ManagerCreateResponseDTO;
import org.group35workingproject.dto.managerDto.ManagerResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ManagerConverter {

    public ManagerCreateResponseDTO toCreateDto(Manager manager) {
        String role = manager.getRole().getName();
        return new ManagerCreateResponseDTO(manager.getId(), manager.getManagerName(), role);
    }

    public ManagerResponseDTO toDto(Manager manager) {
        Role role = manager.getRole();
        return new ManagerResponseDTO(manager.getId(), manager.getManagerName(), manager.getEmail(), role);
    }

    public Manager fromDto(ManagerCreateRequestDTO dto) {
        Manager newManager = new Manager();
        if (dto.getManagerName() != null) {
            newManager.setManagerName(dto.getManagerName());
        }
        if (dto.getPassword() != null) {
            newManager.setPassword(dto.getPassword());
            // newManager.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getEmail() != null) {
            newManager.setEmail(dto.getEmail());
        }

        return newManager;
    }
}
