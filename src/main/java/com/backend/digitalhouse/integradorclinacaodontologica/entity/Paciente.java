package com.backend.digitalhouse.integradorclinacaodontologica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "PACIENTES")
@NoArgsConstructor
@Setter
@Getter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    private Domicilio address;

    private Integer dni;

    private LocalDate fechaDeAlta;

    public Paciente(String name, String lastName, Domicilio address, Integer dni, LocalDate fechaDeAlta) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.dni = dni;
        this.fechaDeAlta = fechaDeAlta;
    }
}
