package com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Turno;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class TurnoResponseDto {
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime day;

    private PacienteTurnoResponseDto turnoPaciente;

    private OdontologoTurnoResponseDto odontologo;
}
