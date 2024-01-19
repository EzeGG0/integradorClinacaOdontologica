package com.backend.digitalhouse.integradorclinacaodontologica.service;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo.OdontologoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;

import java.util.List;


public interface IOdontologoService {

    List<OdontologoResponseDto> listarTodosLosOdontologos();
    OdontologoResponseDto buscarOdontologoPorId(Long id);

    OdontologoResponseDto agregarOdontologo(OdontologoRequestDto odontologoRequestDto);

    void eliminarOdontologo(Long id);

    OdontologoResponseDto actualizarOdontologo(OdontologoModificacionDto odontologoModificado);
}
