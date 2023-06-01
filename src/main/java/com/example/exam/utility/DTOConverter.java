package com.example.exam.utility;

import com.example.exam.entity.Sailboat;
import org.springframework.beans.BeanUtils;

public class DTOConverter {

    public static Sailboat sailboatDTOtoEntity(Sailboat sailboat) {
        Sailboat sailboatEntity = new Sailboat();
        BeanUtils.copyProperties(sailboat, sailboatEntity);
        return sailboatEntity;
    }

}
