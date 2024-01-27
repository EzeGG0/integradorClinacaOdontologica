package com.backend.digitalhouse.integradorclinacaodontologica;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.TurnoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo.OdontologoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.DomicilioRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Turno.TurnoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.TurnoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.OdontologoService;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.PacienteService;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.TurnosService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnosServiceTest {


    @Autowired
    private TurnosService turnosService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Transactional
    public void testAgregarTurno() {
        // Crear un paciente para la prueba
        PacienteResponseDto pacienteDto = pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 421321, new DomicilioRequestDto("calle", 123, "localidad", "provincia")));

        // Crear un odontólogo para la prueba
        OdontologoResponseDto odontologoDto = odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad"));

        // Crear un turno
        TurnoRequestDto turnoDto = new TurnoRequestDto();
        turnoDto.setDay(LocalDateTime.now().plusDays(1)); // Agregar un turno para mañana
        turnoDto.setPacienteSeleccionado(pacienteDto.getId());
        turnoDto.setOdontologoSeleccionado(odontologoDto.getId());

        // Ejecutar el método a probar
        TurnoResponseDto turnoAgregado = turnosService.agregarTurno(turnoDto);

        // Verificar que el turno fue agregado y tiene la misma información
        assertNotNull(turnoAgregado);
        assertEquals(turnoDto.getDay(), turnoAgregado.getDay());
        assertNotNull(turnoAgregado.getTurnoPaciente());
        assertNotNull(turnoAgregado.getOdontologo());
    }

    @Test
    @Transactional
    public void testBuscarPorId() throws Exception {
        // Crear un turno para la prueba
        TurnoResponseDto turnoAgregado = turnosService.agregarTurno(
                new TurnoRequestDto(LocalDateTime.now().plusDays(2),
                        pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 432423, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId(),
                        odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad")).getId()
                )
        );

        // Ejecutar el método a probar
        TurnoResponseDto turnoEncontrado = turnosService.buscarPorId(turnoAgregado.getId());

        // Verificar que el turno fue encontrado y tiene la misma información
        assertNotNull(turnoEncontrado);
        assertEquals(turnoAgregado.getDay(), turnoEncontrado.getDay());
        assertNotNull(turnoEncontrado.getTurnoPaciente());
        assertNotNull(turnoEncontrado.getOdontologo());
    }

    @Test
    @Transactional
    public void testEliminarTurno() {
        // Crear un turno para la prueba
        TurnoResponseDto turnoAgregado = turnosService.agregarTurno(
                new TurnoRequestDto(LocalDateTime.now().plusDays(3),
                        pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 432532, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId(),
                        odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad")).getId()
                )
        );

        // Ejecutar el método a probar
        turnosService.eliminarTurno(turnoAgregado.getId());

        // Verificar que el turno fue eliminado
        assertThrows(Exception.class, () -> turnosService.buscarPorId(turnoAgregado.getId()));
    }

    @Test
    @Transactional
    public void testListarTodosLosTurnos() {
        // Crear varios turnos para la prueba
        turnosService.agregarTurno(
                new TurnoRequestDto(LocalDateTime.now().plusDays(4),
                        pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 432432, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId(),
                        odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad")).getId()
                )
        );

        turnosService.agregarTurno(
                new TurnoRequestDto(LocalDateTime.now().plusDays(5),
                        pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 321432, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId(),
                        odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad")).getId()
                )
        );

        // Ejecutar el método a probar
        List<TurnoResponseDto> turnos = turnosService.listarTodosLosTurnos();

        // Verificar que la lista no sea nula y contenga al menos un turno
        assertNotNull(turnos);
        assertFalse(turnos.isEmpty());
    }

    @Test
    @Transactional
    public void testModificarTurno() {
        // Crear un turno para la prueba
        TurnoResponseDto turnoAgregado = turnosService.agregarTurno(
                new TurnoRequestDto(LocalDateTime.now().plusDays(6),
                        pacienteService.agregarPaciente(new PacienteRequestDto("Nombre", "Apellido", LocalDate.now(), 321432, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId(),
                        odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologo", "ApellidoOdontologo", "Especialidad")).getId()
                )
        );

        // Modificar datos del turno
        TurnoModificacionDto turnoModificadoDto = new TurnoModificacionDto();
        turnoModificadoDto.setId(turnoAgregado.getId());
        turnoModificadoDto.setDay(LocalDateTime.now().plusDays(7));
        turnoModificadoDto.setTurnoPaciente(pacienteService.agregarPaciente(new PacienteRequestDto("NombreModificado", "ApellidoModificado", LocalDate.now(), 4324234, new DomicilioRequestDto("calle", 123, "localidad", "provincia"))).getId());
        turnoModificadoDto.setOdontologo(odontologoService.agregarOdontologo(new OdontologoRequestDto("NombreOdontologoModificado", "ApellidoOdontologoModificado", "EspecialidadModificada")).getId());

        // Ejecutar el método a probar
        TurnoResponseDto turnoModificado = turnosService.modificarTurno(turnoModificadoDto);

        // Verificar que el turno fue modificado y tiene la nueva información
        assertNotNull(turnoModificado);
        assertEquals(turnoAgregado.getId(), turnoModificado.getId());
        assertEquals(turnoModificadoDto.getDay(), turnoModificado.getDay());
        assertNotNull(turnoModificado.getTurnoPaciente());
        assertNotNull(turnoModificado.getOdontologo());
    }
}
