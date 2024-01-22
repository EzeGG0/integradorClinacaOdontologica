package com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Turno;

import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoRequestDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha y hora del turno")
    private LocalDateTime day;

//    @NotNull(message = "El paciente no puede ser nulo")
    private Long pacienteSeleccionado;

    @NotNull(message = "El odontologo no puede ser nulo")
    private Long odontologoSeleccionado;


}
