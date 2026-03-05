package com.example.tienda19.controller;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.request.RequetsFactura;
import com.example.tienda19.response.ResponseFactura;
import com.example.tienda19.service.IFacturaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")

public class FacturaController {
    @Autowired
    IFacturaServices facturaServices;



    @GetMapping("/facturas")
    public List<FacturaEntity> readAll() {
        return facturaServices.readALL();
    }

    @GetMapping("/facturas/{id}")
    public ResponseEntity<FacturaEntity> readById(@PathVariable long id) {
        return facturaServices.readById(id)  // nota: readbyId con b minúscula según tu servicio
                .map(factura -> ResponseEntity.ok(factura))  // si existe, 200 OK
                .orElse(ResponseEntity.notFound().build()); // si no existe, 404
    }

    @PostMapping("/facturas")
    public ResponseEntity<ResponseFactura> create(@Validated @RequestBody RequetsFactura requetsFactura) {

        ResponseFactura responseFactura = facturaServices.create(requetsFactura);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseFactura);
    }
    @PutMapping("/{id}")
    public ResponseFactura update(
            @PathVariable long id,
            @RequestBody RequetsFactura requetsFactura) {

        return facturaServices.update(id, requetsFactura);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarFactura(@PathVariable Long id) {
        try {
            facturaServices.DelateById(id);
            return ResponseEntity.ok("Factura eliminada correctamente.");
        } catch (RuntimeException e) {
            // Retorna 404 si no se encuentra la factura
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
    @GetMapping("/facturas/monto")

    public ResponseEntity<List<FacturaEntity>>
    findByMontoFacturaGreaterThanEqual(
            @RequestParam Double monto) {

        List<FacturaEntity> facturas =
                facturaServices.findByMontoFacturaGreaterThanEqual(monto);

        return ResponseEntity.ok(facturas);
    }


}







