package com.backend.digitalhouse.integradorclinacaodontologica;

import com.backend.digitalhouse.integradorclinacaodontologica.dto.request.Odontologo.OdontologoRequestDto;
import com.backend.digitalhouse.integradorclinacaodontologica.dto.response.Odontologo.OdontologoResponseDto;
import com.backend.digitalhouse.integradorclinacaodontologica.entity.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegradorClinacaOdontologicaApplicationTests {

    @Test
    public void entityADto() {
        Odontologo odontologo = new Odontologo(1L,"Ezequiel", "Gonzalez", "3213123");
        ObjectMapper mapper = new ObjectMapper();
        OdontologoRequestDto odontologoRequestDto = mapper.convertValue(odontologo, OdontologoRequestDto.class);
        OdontologoResponseDto odontologoResponseDto = mapper.convertValue(odontologoRequestDto, OdontologoResponseDto.class);
        Assertions.assertNotNull(odontologoResponseDto);
    }

}
