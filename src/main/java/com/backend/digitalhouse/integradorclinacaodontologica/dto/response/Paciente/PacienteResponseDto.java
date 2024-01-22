package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteResponseDto {
    private Long id;

    private String name;

    private String lastName;

    private DomicilioResponseDto address;

    private Integer dni;

    private LocalDate fechaDeAlta;
}
