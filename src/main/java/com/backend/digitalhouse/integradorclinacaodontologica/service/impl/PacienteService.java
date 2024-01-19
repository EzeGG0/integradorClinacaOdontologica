package com.backend.digitalhouse.integradorclinacaodontologica.service.impl;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Paciente;
import com.backend.digitalhouse.integradorclinacaodontologica.repository.PacienteRepository;
import com.backend.digitalhouse.integradorclinacaodontologica.service.IPacienteService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final PacienteRepository pacienteRepository;
    private final ModelMapper modelMapper;

    public PacienteService(PacienteRepository pacienteRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PacienteResponseDto> listarTodosLosPacientes() {
        return pacienteRepository.findAll().stream().map(paciente -> modelMapper.map(paciente, PacienteResponseDto.class)).toList();
    }

    @Override
    public PacienteResponseDto buscarPorId(Long id) {
        Paciente buscarPacientePorId = pacienteRepository.findById(id).orElse(null);
        PacienteResponseDto buscarPacienteResponseDto = null;
        if(buscarPacientePorId != null) {
            buscarPacienteResponseDto = modelMapper.map(buscarPacientePorId, PacienteResponseDto.class);
        } else LOGGER.error("Paciente no encontrado");
        return buscarPacienteResponseDto;
    }

    @Override
    public PacienteResponseDto agregarPaciente(PacienteRequestDto pacienteRequestDto) {
        Paciente pacienteADEntity = modelMapper.map(pacienteRequestDto, Paciente.class);
        Paciente nuevoPaciente = pacienteRepository.save(pacienteADEntity);

        return modelMapper.map(nuevoPaciente, PacienteResponseDto.class);
    }

    @Override
    public void eliminarPaciente(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public PacienteResponseDto actualizarPaciente(PacienteModificacionDto paciente) {
        Paciente pacienteEntity = modelMapper.map(paciente, Paciente.class);
        Paciente pacienteParaActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteResponseDto pacienteResponseDto = null;

        if(pacienteParaActualizar != null) {
            pacienteParaActualizar = pacienteEntity;
            pacienteRepository.save(pacienteParaActualizar);
            pacienteResponseDto = modelMapper.map(pacienteParaActualizar, PacienteResponseDto.class);
        } else LOGGER.error("No se pudo encontrar el paciente");
        return pacienteResponseDto;
    }

    public void configureMapping() {
        modelMapper.typeMap(PacienteRequestDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteRequestDto::getAddress, Paciente::setAddress));
        modelMapper.typeMap(Paciente.class, PacienteResponseDto.class)
                .addMappings(mapper -> mapper.map(Paciente::getAddress, PacienteResponseDto::setAddress));
        modelMapper.typeMap(PacienteModificacionDto.class, Paciente.class)
                .addMappings(mapper -> mapper.map(PacienteModificacionDto::getAddress, Paciente::setAddress));
    }
}
