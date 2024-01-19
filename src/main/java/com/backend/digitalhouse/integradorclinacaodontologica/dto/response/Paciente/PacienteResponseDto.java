package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente;

import lombok.*;


import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class PacienteResponseDto {
    private Long id;

    private String name;

    private String lastName;

    private DomicilioResponseDto address;

    private Integer dni;

    private LocalDate fechaDeAlta;
}
