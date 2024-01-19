package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class OdontologoResponseDto {
    private Long id;

    private String name;

    private String lastName;

    private String matricula;
}