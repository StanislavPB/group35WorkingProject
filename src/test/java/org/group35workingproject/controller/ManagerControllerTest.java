package org.group35workingproject.controller;

import org.group35workingproject.dto.managerDto.ManagerCreateRequestDTO;
import org.group35workingproject.dto.managerDto.ManagerCreateResponseDTO;
import org.group35workingproject.service.ManagerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
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
        ManagerCreateRequestDTO requestDTO = new ManagerCreateRequestDTO();
        ManagerCreateResponseDTO responseDTO = new ManagerCreateResponseDTO();

        given(managerService.createManager(requestDTO)).willReturn(responseDTO);

        mockMvc.perform(post("/api/managers")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isCreated());

    }

}