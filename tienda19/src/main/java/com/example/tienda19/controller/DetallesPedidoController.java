package com.example.tienda19.controller;

import com.example.tienda19.entity.DetallesPedidoEntity;
import com.example.tienda19.service.impl.DetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DetallesPedidoController {

    @Autowired
    DetallesPedidoService detallesPedidoService;
    @GetMapping("/detalles_pedido")
    public List<DetallesPedidoEntity> readAll(){return detallesPedidoService.readAll();}

    @GetMapping("/detalles_pedido/{id}")
    public DetallesPedidoEntity readById(@PathVariable Integer id){
        return detallesPedidoService.readById(id);

    }
    @PostMapping("/detalles_pedido") //Annotation para crear
    public DetallesPedidoEntity create(@RequestBody DetallesPedidoEntity detallesPedido){
        return detallesPedidoService.create(detallesPedido);
    }
    //Actualizar
    @PutMapping("/detalles_pedido/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody DetallesPedidoEntity detallesPedido){
        return detallesPedidoService.updateById(id,detallesPedido);
    }

    //Borrar
    @DeleteMapping("/detalles_pedido/{id}")
    public String deleteById(@PathVariable Integer id){
        return detallesPedidoService.deleteById(id);
    }
}
