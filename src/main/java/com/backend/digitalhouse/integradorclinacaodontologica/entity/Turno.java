package com.backend.digitalhouse.integradorclinacaodontologica.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "TURNO")
@NoArgsConstructor
@Getter
@Setter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;
    @ManyToOne
    @JoinColumn(name = "pacienteTurno_id", nullable = false)
    private Paciente turnoPaciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    public Turno(LocalDate day, Paciente turnoPaciente, Odontologo odontologo) {
        this.day = day;
        this.turnoPaciente = turnoPaciente;
        this.odontologo = odontologo;
    }
}
