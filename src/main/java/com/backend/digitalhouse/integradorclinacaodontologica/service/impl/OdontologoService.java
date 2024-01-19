package com.backend.digitalhouse.integradorclinacaodontologica.service.impl;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo.OdontologoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import com.backend.digitalhouse.integradorclinacaodontologica.repository.OdontologoRepository;
import com.backend.digitalhouse.integradorclinacaodontologica.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private final OdontologoRepository odontologoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<OdontologoResponseDto> listarTodosLosOdontologos() {
        List<OdontologoResponseDto> listaOdontologos =  odontologoRepository.findAll().stream()
                .map(o -> modelMapper.map(o, OdontologoResponseDto.class)).toList();
        LOGGER.info("Listado de todos los odontologos: ", listaOdontologos);
        return listaOdontologos;
    }

    @Override
    public OdontologoResponseDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoResponseDto odontologoResponse = null;
        if (odontologoBuscado != null) {
            odontologoResponse = modelMapper.map(odontologoBuscado, OdontologoResponseDto.class);
            LOGGER.info("Odontologo encontrado: ", odontologoResponse);
        } else {
            LOGGER.error("El ID no se encuentra registrado en la base de datos");
            return null;
        }
        return odontologoResponse;
    }

    @Override
    public OdontologoResponseDto agregarOdontologo(OdontologoRequestDto odontologoRequestDto) {
        Odontologo nuevoOdontologo = odontologoRepository.save(odontologoRequestDtoAOdontologoEntity(odontologoRequestDto));
        return modelMapper.map(nuevoOdontologo, OdontologoResponseDto.class);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public OdontologoResponseDto actualizarOdontologo(OdontologoModificacionDto odontologoModificado) {
        Odontologo odontologoRecibido = modelMapper.map(odontologoModificado, Odontologo.class);
        Odontologo odontologoSeleccionadoParaModificar = odontologoRepository.findById(odontologoModificado.getId()).orElse(null);
        OdontologoResponseDto odontologoResponseDto = null;

        if(odontologoSeleccionadoParaModificar != null) {
            odontologoSeleccionadoParaModificar = odontologoRecibido;
            odontologoRepository.save(odontologoSeleccionadoParaModificar);
            odontologoResponseDto =  modelMapper.map(odontologoSeleccionadoParaModificar, OdontologoResponseDto.class);
            LOGGER.info("Odontologo Actualizado: " + odontologoResponseDto);
        } else {
            LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");
        }
        return odontologoResponseDto;
    }

    // Transformacion de Dto a Entity
    private Odontologo odontologoRequestDtoAOdontologoEntity(OdontologoRequestDto odontologoRequest) {
        return modelMapper.map(odontologoRequest, Odontologo.class);
    }
}
