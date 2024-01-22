package com.backend.digitalhouse.integradorclinacaodontologica.service;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.TurnoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Turno.TurnoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.TurnoResponseDto;

import java.util.List;

/**
 * Registrar turno:
 * listar,
 * agregar,
 * modificar
 * y eliminar pacientes.
 */
public interface ITurnoService {

    TurnoResponseDto agregarTurno(TurnoRequestDto nuevoTurno);

    TurnoResponseDto buscarPorId(Long id) throws Exception;

    void eliminarTurno(Long id);

    List<TurnoResponseDto> listarTodosLosTurnos();


    TurnoResponseDto modificarTurno(TurnoModificacionDto turnoModificado);
}
