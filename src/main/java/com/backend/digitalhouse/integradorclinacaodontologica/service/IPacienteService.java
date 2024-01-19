package com.backend.digitalhouse.integradorclinacaodontologica.service;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;

import java.util.List;


public interface IPacienteService {

    List<PacienteResponseDto> listarTodosLosPacientes();

    PacienteResponseDto buscarPorId(Long id);

    PacienteResponseDto agregarPaciente(PacienteRequestDto pacienteRequestDto);

    void eliminarPaciente(Long id);

    PacienteResponseDto actualizarPaciente(PacienteModificacionDto paciente);
}
