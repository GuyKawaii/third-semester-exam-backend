package com.example.tourdefrancebackend.utility;

import com.example.tourdefrancebackend.dto.CykelholdDTO;
import com.example.tourdefrancebackend.dto.CykelrytterDTO;
import com.example.tourdefrancebackend.dto.JerseysDTO;
import com.example.tourdefrancebackend.entity.Cykelhold;
import com.example.tourdefrancebackend.entity.Cykelrytter;
import com.example.tourdefrancebackend.enums.Jersey;
import org.springframework.beans.BeanUtils;

public class DTOConverter {
    public static CykelrytterDTO cykelrytterToDTO(Cykelrytter cykelrytter) {
        CykelrytterDTO cykelrytterDTO = new CykelrytterDTO();
        BeanUtils.copyProperties(cykelrytter, cykelrytterDTO);
        cykelrytterDTO.setCykelhold(convertToCykelholdDTO(cykelrytter.getCykelhold()));
        return cykelrytterDTO;
    }

    private static CykelholdDTO convertToCykelholdDTO(Cykelhold cykelhold) {
        return new CykelholdDTO(cykelhold.getId(), cykelhold.getNavn());
    }

    public static JerseysDTO convertToJerseysDTO(Cykelrytter cykelrytter, String jersey) {
        JerseysDTO jerseysDTO = new JerseysDTO();
        BeanUtils.copyProperties(cykelrytter, jerseysDTO);
        jerseysDTO.setCykelhold(convertToCykelholdDTO(cykelrytter.getCykelhold()));
        jerseysDTO.setJersey(jersey);
        return jerseysDTO;
    }
}
