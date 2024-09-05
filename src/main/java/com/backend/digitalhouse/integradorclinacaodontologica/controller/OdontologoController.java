package com.backend.digitalhouse.integradorclinacaodontologica.controller;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo.OdontologoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/odontologo")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @PostMapping
    public ResponseEntity<OdontologoResponseDto> agregarNuevoOdontologo(@RequestBody OdontologoRequestDto odontologoRequest) {
        return new ResponseEntity<>(odontologoService.agregarOdontologo(odontologoRequest), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<OdontologoResponseDto>> getAllOdontologos() {
        return new ResponseEntity<>(odontologoService.listarTodosLosOdontologos(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<OdontologoResponseDto> getOdontologoById(@PathVariable Long id) {
        if(odontologoService.buscarOdontologoPorId(id) == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOdontologoById(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo con id: " + id + " eliminado correctamente", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<OdontologoResponseDto> actualizarOdontologoPorId(@Valid @RequestBody OdontologoModificacionDto odontologoModificacionDto) {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoModificacionDto),
                HttpStatus.OK);
    }
}
