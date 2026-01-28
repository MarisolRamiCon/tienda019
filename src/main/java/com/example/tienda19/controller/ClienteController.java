package com.example.tienda19.controller;

import com.example.tienda19.entity.ClienteEntity;
import com.example.tienda19.service.impl.ClienteService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class ClienteController {
    @Autowired
    ClienteService clienteService;
    @GetMapping("/clientes")
    public List<ClienteEntity> readAll(){
        return clienteService.readAll();
    }
    @GetMapping("/clientes/{id}")
    public ClienteEntity readById(@PathVariable Integer id){
        return clienteService.readById(id);

    }

    @PostMapping("/clientes") //Annotation para crear
    public ClienteEntity create(@RequestBody ClienteEntity departamento){
        return clienteService.create(departamento);
    }

    //Actualizar
    @PutMapping("/clientes/{id}")
    public String updateById(@PathVariable Integer id, @RequestBody ClienteEntity clientes){
        return clienteService.updateById(id,clientes);
    }

    //Borrar
    @DeleteMapping("/clientes/{id}")
    public String deleteById(@PathVariable Integer id){
        return clienteService.deleteById(id);
    }

    //Metodo personalizado con palabras clave
    @GetMapping("/nombreAnddireccion")
    public List<ClienteEntity> nombreAnddireccion(@PathParam("nombre") String nombre,@PathParam("direccion") String direccion){
        return clienteService.nombreAnddireccion (nombre,direccion);

    }

    //Metodo personalizado con Query
    @GetMapping("/nombreAnddireccionQuery")
    public List<ClienteEntity> nombreAnddireccionQ(@PathParam("nombre") String nombre,@PathParam("direccion") String direccion){
        return clienteService.nombreAnddireccionQ(nombre,direccion);

    }

}




