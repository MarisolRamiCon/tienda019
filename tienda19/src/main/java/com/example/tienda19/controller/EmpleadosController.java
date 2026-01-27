package com.example.tienda19.controller;

import com.example.tienda19.entity.EmpleadosEntity;
import com.example.tienda19.service.impl.EmpleadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class EmpleadosController {

    @Autowired
    EmpleadosService empleadosService;
    @GetMapping("/empleados")
    public List<EmpleadosEntity> readAll(){
        return empleadosService.readAll();
    }
}
