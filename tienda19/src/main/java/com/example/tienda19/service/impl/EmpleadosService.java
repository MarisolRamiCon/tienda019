package com.example.tienda19.service.impl;

import com.example.tienda19.entity.EmpleadosEntity;
import com.example.tienda19.repository.EmpleadosRepository;
import com.example.tienda19.service.IEmpleadosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmpleadosService implements IEmpleadosService {

    @Autowired
    EmpleadosRepository empleadosRepository;

    @Override
    public List<EmpleadosEntity> readAll() {
        log.info("Se consulto exitosamente la lista de empleados");
        return empleadosRepository.findAll();
    }

}
