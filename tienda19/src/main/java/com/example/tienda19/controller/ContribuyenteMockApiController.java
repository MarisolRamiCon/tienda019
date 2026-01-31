package com.example.tienda19.controller;

import com.example.tienda19.model.ContribuyenteMockApi;
import com.example.tienda19.service.impl.ServicesContribuyente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/contribuyente")
public class ContribuyenteMockApiController {
    @Autowired
    private ServicesContribuyente servicesContribuyente;

    @GetMapping("/Contribuyente")
    List<ContribuyenteMockApi> readALL() {
        return servicesContribuyente.readALL();
    }

    @GetMapping("/Contribuyente/{id}")
    ContribuyenteMockApi readByID(@PathVariable Integer id) {
        return servicesContribuyente.readById(id);

    }

    @PostMapping("CrearContribuyente")
    String create(@RequestBody ContribuyenteMockApi contribuyenteMockApi) {
        String c = "Se creo nuevo registro";
        return c + servicesContribuyente.create(contribuyenteMockApi);
    }
    @PutMapping("Actualizar")
    String update(@RequestBody ContribuyenteMockApi contribuyenteMockApi, @PathVariable Integer id){servicesContribuyente.update(id, contribuyenteMockApi);
    String a = "Actualiza El campo";
     return a + id;
    }
    @DeleteMapping("Eliminar")
    String DelateLogico(@PathVariable Integer id){return servicesContribuyente.delateLogico(id);}
}










