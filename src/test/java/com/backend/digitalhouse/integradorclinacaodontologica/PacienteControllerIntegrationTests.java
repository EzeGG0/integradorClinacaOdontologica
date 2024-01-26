package com.backend.digitalhouse.integradorclinacaodontologica;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.PacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class PacienteControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PacienteService pacienteService;

    @Test
    void testAgregarPaciente() throws Exception {
        PacienteRequestDto pacienteRequestDto = new PacienteRequestDto();
        pacienteRequestDto.setName("Nombre");
        pacienteRequestDto.setLastName("Apellido");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteRequestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nombre"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Apellido"));
    }

    @Test
    void testActualizarPaciente() throws Exception {
        // Supongamos que hay un paciente existente en la base de datos con el id 1
        Long pacienteId = 1L;

        PacienteModificacionDto pacienteModificacionDto = new PacienteModificacionDto();
        pacienteModificacionDto.setId(pacienteId);
        pacienteModificacionDto.setName("NombreActualizado");
        pacienteModificacionDto.setLastName("ApellidoActualizado");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/paciente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pacienteModificacionDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("NombreActualizado"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("ApellidoActualizado"));
    }

    @Test
    void testEliminarPaciente() throws Exception {
        // Supongamos que hay un paciente existente en la base de datos con el id 1
        Long pacienteId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/paciente/{id}", pacienteId))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
