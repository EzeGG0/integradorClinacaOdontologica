package com.backend.digitalhouse.integradorclinacaodontologica.repository;

import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
