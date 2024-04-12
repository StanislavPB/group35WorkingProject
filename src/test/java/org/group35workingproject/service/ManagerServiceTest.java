package org.group35workingproject.service;

import org.group35workingproject.domain.Manager;
import org.group35workingproject.domain.Role;
import org.group35workingproject.domain.Task;
import org.group35workingproject.dto.managerDto.ManagerCreateRequestDTO;
import org.group35workingproject.dto.taskDto.TaskCreateRequestDTO;
import org.group35workingproject.repository.ManagerRepository;
import org.group35workingproject.repository.RoleRepository;
import org.group35workingproject.repository.TaskRepository;
import org.group35workingproject.service.exception.NotFoundException;
import org.group35workingproject.service.util.ManagerConverter;
import org.group35workingproject.service.util.TaskConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
class ManagerServiceTest {


    private ManagerService managerService;

    @Mock
    private ManagerRepository managerRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ManagerConverter managerConverter;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        managerService = new ManagerService(managerRepository,roleRepository,managerConverter);
    }



    @Test
    void testFindByManagerName(){
       when(managerRepository.findByManagerName(anyString())).thenReturn(Optional.of(new Manager()));
       managerService.findByManagerName("testManager");
       verify(managerRepository,times(1)).findByManagerName(anyString());
    }

    @Test
    void testFindByManagerNameForCreateTask() {
        when(managerRepository.findByManagerName(anyString())).thenReturn(Optional.of(new Manager()));
        managerService.findByManagerNameForCreateTask("testManager");
        verify(managerRepository,times(1)).findByManagerName(anyString());
    }


    @Test
    void testCreateManager() {

        ManagerCreateRequestDTO requestDTO = new ManagerCreateRequestDTO();
        requestDTO.setManagerName("testManager");

        when(managerRepository.findByManagerName(anyString())).thenReturn(Optional.empty());
        when(roleRepository.findByName(anyString())).thenReturn(Optional.of(new Role(1,"User")));
        when(managerConverter.fromDto(any(ManagerCreateRequestDTO.class))).thenReturn(new Manager());

        managerService.createManager(requestDTO);

        verify(managerRepository,times(1)).save(any(Manager.class));


    }


    @Test
    void testFindByManagerNameManagerNotFound() {
        when(managerRepository.findByManagerName(anyString())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            managerService.findByManagerName("managerName");
        });
    }

}