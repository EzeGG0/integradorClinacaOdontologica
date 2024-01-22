package com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Paciente;

import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteRequestDto {

    @NotNull(message = "El nombre del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del paciente")
    private String name;

    @NotNull(message = "El apellido del paciente no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del paciente")
    private String lastName;

    @FutureOrPresent(message = "La fecha no puede ser anterior al dia de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private DomicilioRequestDto address;

    @NotNull(message = "El dni del paciente no puede ser nulo")
    private Integer dni;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    @NotNull(message = "El domicilio del paciente no puede ser nulo")
    @Valid
    private LocalDate fechaDeAlta;

}
