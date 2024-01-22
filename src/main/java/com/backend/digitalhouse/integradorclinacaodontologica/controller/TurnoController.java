package com.backend.digitalhouse.integradorclinacaodontologica.controller;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.TurnoModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Turno.TurnoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno.TurnoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.TurnosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/turno")
public class TurnoController {

    private final TurnosService turnosService;

    public TurnoController(TurnosService turnos, TurnosService turnosService) {
        this.turnosService = turnosService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<TurnoResponseDto>> getAllTurnos() {
        return new ResponseEntity<>(turnosService.listarTodosLosTurnos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> getTurnoById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(turnosService.buscarPorId(id), HttpStatus.OK);
    }

    // POST
    @PostMapping("/post")
    public ResponseEntity<TurnoResponseDto> createTurno(@Valid @RequestBody TurnoRequestDto nuevoTurno) {
        return new ResponseEntity<>(turnosService.agregarTurno(nuevoTurno), HttpStatus.CREATED);
    }

    // DELTE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTurnoById(@PathVariable Long id) {
        turnosService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado con exito", HttpStatus.OK);
    }

    // PUT
    @PutMapping
    public ResponseEntity<TurnoResponseDto> updateTurno(@Valid @RequestBody TurnoModificacionDto turnoModificado) {
        return new ResponseEntity<>(turnosService.modificarTurno(turnoModificado), HttpStatus.OK);
    }
}
