package com.backend.digitalhouse.integradorclinacaodontologica;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.DomicilioModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.DomicilioRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.repository.PacienteRepository;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.PacienteService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Test
    @Transactional
    public void testListarTodosLosPacientes() {
        // Agregar pacientes para la prueba
        PacienteRequestDto pacienteDto = new PacienteRequestDto();
        DomicilioRequestDto domicilioDto = new DomicilioRequestDto("calle", 123, "localidad", "provincia");
        pacienteDto.setName("Juan");
        pacienteDto.setAddress(domicilioDto);

        pacienteService.agregarPaciente(pacienteDto);

        // Ejecutar el método a probar
        List<PacienteResponseDto> pacientes = pacienteService.listarTodosLosPacientes();

        // Verificar que la lista no sea nula y contenga al menos un paciente
        assertNotNull(pacientes);
        assertFalse(pacientes.isEmpty());
    }

    @Test
    @Transactional
    public void testBuscarPorId() {
        // Agregar paciente para la prueba
        PacienteRequestDto pacienteDto = new PacienteRequestDto();
        DomicilioRequestDto domicilioDto = new DomicilioRequestDto("calle", 123, "localidad", "provincia");
        pacienteDto.setName("Maria");
        pacienteDto.setAddress(domicilioDto);

        PacienteResponseDto pacienteAgregado = pacienteService.agregarPaciente(pacienteDto);

        // Ejecutar el método a probar
        PacienteResponseDto pacienteEncontrado = pacienteService.buscarPorId(pacienteAgregado.getId());

        // Verificar que el paciente fue encontrado y tiene la misma información
        assertNotNull(pacienteEncontrado);
        assertEquals(pacienteAgregado.getName(), pacienteEncontrado.getName());
    }

    @Test
    @Transactional
    public void testAgregarPaciente() {
        // Crear paciente para agregar
        PacienteRequestDto pacienteDto = new PacienteRequestDto();
        DomicilioRequestDto domicilioDto = new DomicilioRequestDto("calle", 123, "localidad", "provincia");
        pacienteDto.setName("Carlos");
        pacienteDto.setAddress(domicilioDto);

        // Ejecutar el método a probar
        PacienteResponseDto pacienteAgregado = pacienteService.agregarPaciente(pacienteDto);

        // Verificar que el paciente fue agregado y tiene la misma información
        assertNotNull(pacienteAgregado);
    }

    @Test
    @Transactional
    public void testEliminarPaciente() {
        // Agregar paciente para la prueba
        PacienteRequestDto pacienteDto = new PacienteRequestDto();
        DomicilioRequestDto domicilioDto = new DomicilioRequestDto("calle", 123, "localidad", "provincia");
        pacienteDto.setName("Laura");
        pacienteDto.setAddress(domicilioDto);

        PacienteResponseDto pacienteAgregado = pacienteService.agregarPaciente(pacienteDto);

        // Ejecutar el método a probar
        pacienteService.eliminarPaciente(pacienteAgregado.getId());

        // Verificar que el paciente fue eliminado
        assertNull(pacienteService.buscarPorId(pacienteAgregado.getId()));
    }

    @Test
    @Transactional
    public void testActualizarPaciente() {
        // Agregar paciente para la prueba
        PacienteRequestDto pacienteDto = new PacienteRequestDto();
        DomicilioRequestDto domicilioDto = new DomicilioRequestDto("calle", 123, "localidad", "provincia");
        pacienteDto.setName("Pedro");
        pacienteDto.setAddress(domicilioDto);

        PacienteResponseDto pacienteAgregado = pacienteService.agregarPaciente(pacienteDto);

        // Modificar datos del paciente
        PacienteModificacionDto pacienteModificadoDto = new PacienteModificacionDto();
        DomicilioRequestDto domicilioModificadoDto = new DomicilioRequestDto("calle", 213, "localidad", "provincia");

        pacienteModificadoDto.setId(pacienteAgregado.getId());
        pacienteModificadoDto.setAddress(domicilioModificadoDto);

        // Ejecutar el método a probar
        PacienteResponseDto pacienteModificado = pacienteService.actualizarPaciente(pacienteModificadoDto);

        // Verificar que el paciente fue modificado y tiene la nueva información
        assertNotNull(pacienteModificado);
        assertEquals(pacienteAgregado.getId(), pacienteModificado.getId());
        assertNotEquals(pacienteAgregado.getAddress(), pacienteModificado.getAddress());
    }
}
