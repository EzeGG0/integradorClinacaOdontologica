package com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TurnoModificacionDto {
    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime day;

    @NotNull(message = "El paciente no puede ser nulo")
    private Long turnoPaciente;

    @NotNull(message = "El odontologo no puede ser nulo")
    private Long odontologo;
}
