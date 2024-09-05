package com.backend.digitalhouse.integradorclinacaodontologica.controller;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente.PacienteRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente.PacienteResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.service.impl.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<PacienteResponseDto>> getAllPacientes() {
        return new ResponseEntity<>(pacienteService.listarTodosLosPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> getPacienteById(@PathVariable Long id) {
        return new ResponseEntity<>(pacienteService.buscarPorId(id), HttpStatus.OK);
    }

    // POST
    @PostMapping
    public ResponseEntity<PacienteResponseDto> postPaciente(@RequestBody PacienteRequestDto paciente) {
        return new ResponseEntity<>(pacienteService.agregarPaciente(paciente), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePacienteById(@PathVariable Long id) {
        pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Se elemino el paciente con exito", HttpStatus.OK);
    }

    // PUT

    @PutMapping ResponseEntity<PacienteResponseDto> putPaciente(@RequestBody PacienteModificacionDto pacienteModificado) {
        return new ResponseEntity<>(pacienteService.actualizarPaciente(pacienteModificado), HttpStatus.OK);
    }
}
