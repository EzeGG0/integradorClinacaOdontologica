package com.backend.digitalhouse.integradorclinacaodontologica.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "TURNOS")
@NoArgsConstructor
@AllArgsConstructor
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDay() {
        return day;
    }

    public void setDay(LocalDateTime day) {
        this.day = day;
    }

    public Paciente getTurnoPaciente() {
        return turnoPaciente;
    }

    public void setTurnoPaciente(Paciente turnoPaciente) {
        this.turnoPaciente = turnoPaciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }
}
