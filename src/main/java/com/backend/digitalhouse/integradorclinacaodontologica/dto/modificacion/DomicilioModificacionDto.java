package com.backend.digitalhouse.integradorclinacaodontologica.dto.modificacion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DomicilioModificacionDto {

    @NotNull(message = "Debe proveer el Id del domicilio que se desea modificar")
    private Long id;
    @NotNull(message = "El campo calle no puede ser nulo")
    @NotBlank(message = "El campo calle no puede estar en blanco")
    private String calle;

    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    @NotNull(message = "El campo numero no puede ser nulo")
    private int numero;

    @NotNull(message = "El campo localidad no puede ser nulo")
    @NotBlank(message = "El campo localidad no puede estar en blanco")
    private String localidad;

    @NotNull(message = "El campo provincia no puede ser nulo")
    @NotBlank(message = "El campo provincia no puede estar en blanco")
    private String provincia;
}
