package com.backend.digitalhouse.integradorclinacaodontologica.repository;

import com.backend.digitalhouse.integradorclinacaodontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
