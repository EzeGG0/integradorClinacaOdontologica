package com.backend.digitalhouse.integradorclinacaodontologica.repository;

import com.backend.digitalhouse.integradorclinacaodontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
