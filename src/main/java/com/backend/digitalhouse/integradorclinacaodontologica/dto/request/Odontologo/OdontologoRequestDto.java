package com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OdontologoRequestDto {

    @NotBlank(message = "El nombre no puede estar en blanco")
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    @NotBlank(message = "El apellido no puede estar en blanco")
    @NotNull(message = "El apellido no puede ser nulo")
    private String lastName;

    @NotNull(message = "La matricula del odontólogo no puede ser nula")
    @NotBlank(message = "Debe especificarse la matricula del odontólogo")
    private String matricula;

}
