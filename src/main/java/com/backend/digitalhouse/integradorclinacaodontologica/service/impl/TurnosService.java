package com.backend.digitalhouse.integradorclinacaodontologica.service.impl;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.TurnoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Turno.TurnoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.OdontologoTurnoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.PacienteTurnoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.TurnoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Paciente;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Turno;
import com.backend.digitalhouse.integradorclinacaodontologica.repository.TurnoRepository;
import com.backend.digitalhouse.integradorclinacaodontologica.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TurnosService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnosService.class);
    private final ModelMapper modelMapper;
    private final TurnoRepository turnoRepository;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnosService(ModelMapper modelMapper, TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.modelMapper = modelMapper;
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDto agregarTurno(TurnoRequestDto nuevoTurno) {
        TurnoResponseDto turnoResponseDto = null;
        Long turnoPacienteSeleccionadoId = nuevoTurno.getPacienteSeleccionado();
        Long odontologoSeleccionadoId = nuevoTurno.getOdontologoSeleccionado();
        PacienteResponseDto paciente = pacienteService.buscarPorId(turnoPacienteSeleccionadoId);
        OdontologoResponseDto odontologo = odontologoService.buscarOdontologoPorId(odontologoSeleccionadoId);
        Paciente pacienteTurno = modelMapper.map(paciente, Paciente.class);
        Odontologo odontologoTurno = modelMapper.map(odontologo, Odontologo.class);
        String pacienteNoEncontradoEnDb = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEncontradoEnDb = "El odontologo no se encuentra en nuestra base de datos";


        if (paciente == null ||  odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en nuestra base de datos");
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEncontradoEnDb);
            } else {
                LOGGER.error(odontologoNoEncontradoEnDb);
            }
        } else {
            Turno turnoParaAgregar = modelMapper.map(nuevoTurno, Turno.class);
            turnoParaAgregar.setTurnoPaciente(pacienteTurno);
            turnoParaAgregar.setOdontologo(odontologoTurno);
            turnoRepository.save(turnoParaAgregar);
            turnoResponseDto = turnoEntityATurnoResponseDto(turnoParaAgregar);
            LOGGER.info("Turno agregado con exito");
        }
        return turnoResponseDto;
    }

    @Override
    public TurnoResponseDto buscarPorId(Long id) throws Exception {
        Turno turnoBuscadoPorId = turnoRepository.findById(id).orElse(null);

        TurnoResponseDto turnoResponseDto = null;
        if(turnoBuscadoPorId != null) {
            turnoResponseDto = turnoEntityATurnoResponseDto(turnoBuscadoPorId);
        } else {
            LOGGER.error("No se pudo encontrar el turno por ID en la base de datos");
            System.out.println(Arrays.toString(new Exception().getStackTrace()));
            throw new Exception();
        }

        return turnoResponseDto;
    }

    @Override
    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
        LOGGER.info("Se elimino el turno con  id:" + id);
    }

    @Override
    public List<TurnoResponseDto> listarTodosLosTurnos() {
        LOGGER.info("Listando los turnos");
        return turnoRepository.findAll().stream().map(turno -> modelMapper.map(turno, TurnoResponseDto.class)).toList();
    }

    @Override
    public TurnoResponseDto modificarTurno(TurnoModificacionDto turnoModificado) {
        Turno turnoBuscadoParaActualizar = turnoRepository.findById(turnoModificado.getId()).orElse(null);
        TurnoResponseDto turnoResponseDto = null;

        if(turnoBuscadoParaActualizar != null) {
            turnoBuscadoParaActualizar.setTurnoPaciente(modelMapper.map(pacienteService.buscarPorId(turnoModificado.getTurnoPaciente()), Paciente.class));

            turnoBuscadoParaActualizar.setOdontologo(modelMapper.map(odontologoService.buscarOdontologoPorId(turnoModificado.getOdontologo()), Odontologo.class));

            turnoBuscadoParaActualizar.setDay(turnoModificado.getDay());

            turnoRepository.save(turnoBuscadoParaActualizar);

            turnoResponseDto = turnoEntityATurnoResponseDto(turnoBuscadoParaActualizar);

            LOGGER.info("Turno modificado");
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");
        }

        return turnoResponseDto;
    }


    public PacienteTurnoResponseDto pacienteTurnoResponseDtoATurnoResponseDto(Long id){
        return modelMapper.map(pacienteService.buscarPorId(id), PacienteTurnoResponseDto.class);
    }

    public OdontologoTurnoResponseDto odontologoTurnoResponseDtoATurnoResponseDto(Long id) {
        return modelMapper.map(odontologoService.buscarOdontologoPorId(id), OdontologoTurnoResponseDto.class);
    }

    public TurnoResponseDto turnoEntityATurnoResponseDto(Turno turno) {


        if (turno == null || turno.getTurnoPaciente() == null || turno.getOdontologo() == null) {
            // Manejar el caso cuando alguna de las propiedades sea nula
            return null;
        }

        try {
            TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
            turnoResponseDto.setTurnoPaciente(pacienteTurnoResponseDtoATurnoResponseDto(turno.getTurnoPaciente().getId()));
            turnoResponseDto.setOdontologo(odontologoTurnoResponseDtoATurnoResponseDto(turno.getOdontologo().getId()));
            return turnoResponseDto;
        } catch (Exception e) {
            LOGGER.error("Error al mapear el turno a TurnoResponseDto", e);
            return null;
        }
    }
}
