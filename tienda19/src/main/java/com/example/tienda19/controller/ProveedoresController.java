package com.example.tienda19.controller;

import com.example.tienda19.entity.ProveedoresEntity;
import com.example.tienda19.service.impl.ProveedoresService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/proveedores/{id}")
    public ProveedoresEntity readById(@PathVariable Integer id){
        return proveedoresService.readById(id);
    }
    @PostMapping("/proveedores")
    public ProveedoresEntity create(@RequestBody ProveedoresEntity proveedoresEntity){
        return proveedoresService.create(proveedoresEntity);
    }
    @PutMapping("proveedores/{id}")
    public String update(@PathVariable Integer id,@RequestBody ProveedoresEntity proveedores){
      return proveedoresService.update(id,proveedores);
    }
    @DeleteMapping("/proveedores/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        boolean deleted = proveedoresService.delete(id);
        if (deleted) {
            return ResponseEntity.ok("Proveedor eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Proveedor no encontrado");
        }
    }
    @GetMapping("contacto/{id}")
    public String getContacto(@PathVariable Integer id){
        return proveedoresService.obtnerContacto(id);
    }
}
