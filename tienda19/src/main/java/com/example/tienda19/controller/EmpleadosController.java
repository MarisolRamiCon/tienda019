package com.example.tienda19.controller;

import com.example.tienda19.entity.EmpleadosEntity;
import com.example.tienda19.service.impl.EmpleadosService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmpleadosController {
    @Autowired
    EmpleadosService empleadosService;
    @GetMapping("/empleados")
    public List<EmpleadosEntity> readAll(){
        return empleadosService.readAll();
    }
    @GetMapping("/empleados/{id}")
    public EmpleadosEntity readById(@PathVariable Integer id){
        return empleadosService.readById(id);

    }
    @PostMapping("/empleados") //Annotation para crear
    public EmpleadosEntity create(@RequestBody EmpleadosEntity empleados){
        return empleadosService.create(empleados);
    }
    //Actualizar
    @PutMapping("/empleados/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody EmpleadosEntity empleados){
        return empleadosService.updateById(id,empleados);
    }

    //Borrar
    @DeleteMapping("/empleados/{id}")
    public String deleteById(@PathVariable Integer id){
        return empleadosService.deleteById(id);
    }
}
