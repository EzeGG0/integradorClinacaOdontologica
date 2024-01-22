package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Paciente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomicilioResponseDto {
    private Long id;

    private String calle;

    private int numero;

    private String localidad;

    private String provincia;
}
