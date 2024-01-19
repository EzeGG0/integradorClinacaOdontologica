package com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OdontologoModificacionDto {

    @NotNull(message = "Debe proveerse el id del odontologo que se desea modificar")
    private Long id;

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
