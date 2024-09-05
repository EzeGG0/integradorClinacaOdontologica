package com.backend.digitalhouse.integradorclinacaodontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "ODONTOLOGOS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String lastName;

    private String matricula;


    public Odontologo(String name, String lastName, String matricula) {
        this.name = name;
        this.lastName = lastName;
        this.matricula = matricula;
    }
}
