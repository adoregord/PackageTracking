package com.example.tracking.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.tracking.model.Paket;
import com.example.tracking.service.PaketService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureWebMvc
public class PaketControllerTest {

    @MockBean
    private PaketService paketService;

    @Autowired
    private PaketController paketController;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        // MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(paketController).build();

    }

    @Test
    public void GetAllPaket() throws Exception {
        List<Paket> paketList = Arrays.asList(new Paket(), new Paket());
        when(paketService.getAllPaket()).thenReturn(paketList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/paket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[1]").exists());

        verify(paketService, times(1)).getAllPaket();
    }

    @Test
    public void AddPaket() throws Exception {
        Paket paket = new Paket();
        paket.setId_paket(1L);
        when(paketService.addPaket(any(Paket.class))).thenReturn(paket);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/paket")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paket)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id_paket").value(1L));

        verify(paketService, times(1)).addPaket(paket);
    }

    @Test
    public void GetCheckpointPaket() throws Exception {
        List<Object> checkpointPaket = new ArrayList<>();
        Paket paket1 = new Paket();
        Paket paket2 = new Paket();

        checkpointPaket.add(paket1);
        checkpointPaket.add(paket2);
        when(paketService.getCheckpointPaket(anyString())).thenReturn(checkpointPaket);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/paket/{checkpoint}", "Jakarta"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()")
                        .value(2));

        verify(paketService, times(1)).getCheckpointPaket(anyString());

    }
}
