package com.backend.digitalhouse.integradorclinacaodontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dataDay")
    private LocalDateTime day;

    @ManyToOne
    @JoinColumn(name = "pacienteTurno_id")
    private Paciente turnoPaciente;

    @ManyToOne
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;

    public Turno(LocalDateTime day, Paciente turnoPaciente, Odontologo odontologo) {
        this.day = day;
        this.turnoPaciente = turnoPaciente;
        this.odontologo = odontologo;
    }
}
