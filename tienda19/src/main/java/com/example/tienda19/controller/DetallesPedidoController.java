package com.example.tienda19.controller;

import com.example.tienda19.entity.DetallesPedidoEntity;
import com.example.tienda19.service.impl.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DetallesPedidoController {

    @Autowired
    DetallesPedidoService detallesPedidoService;
    @GetMapping("/detallesPedido")
    public List<DetallesPedidoEntity> readAll(){return detallesPedidoService.readAll();
    }
}
