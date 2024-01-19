package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoTurnoResponseDto {
    private Long id;

    private String name;

    private String lastName;
}
