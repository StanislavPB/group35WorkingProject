package org.group35workingproject.controller;

import org.group35workingproject.dto.managerDto.ManagerCreateRequestDTO;
import org.group35workingproject.dto.managerDto.ManagerCreateResponseDTO;
import org.group35workingproject.service.ManagerService;
import org.group35workingproject.service.exception.AlreadyExistException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ManagerController.class)
class ManagerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ManagerService managerService;

    @Test
    public void createManager() throws Exception {

        ManagerCreateResponseDTO responseDTO = new ManagerCreateResponseDTO();
        responseDTO.setId(1); // Установка ID для ответа
        responseDTO.setManagerName("Test Manager");
        responseDTO.setRoleName("Admin");

        when(managerService.createManager(any(ManagerCreateRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"managerName\":\"John\", \"roleName\":\"Admin\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.managerName").value("Test Manager"))
                .andExpect(jsonPath("$.roleName").value("Admin"));

    }



}