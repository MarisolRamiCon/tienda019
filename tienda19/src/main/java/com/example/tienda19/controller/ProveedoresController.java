package com.example.tienda19.controller;

import com.example.tienda19.entity.ProveedoresEntity;
import com.example.tienda19.service.impl.ProveedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProveedoresController {
    @Autowired
    ProveedoresService proveedoresService;
    @GetMapping("/proveedores")
    public List<ProveedoresEntity> readAll(){
        return proveedoresService.readAll();
    }
}
