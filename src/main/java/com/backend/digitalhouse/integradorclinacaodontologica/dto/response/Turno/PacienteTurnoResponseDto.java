package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteTurnoResponseDto {
    private Long id;

    private String name;

    private String lastName;
}
