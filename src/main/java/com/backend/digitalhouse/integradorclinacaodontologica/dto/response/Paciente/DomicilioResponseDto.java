package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DomicilioResponseDto {
    private Long id;

    private String calle;

    private int numero;

    private String localidad;

    private String provincia;
}
