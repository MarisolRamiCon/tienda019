package com.example.tienda19.controller;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.service.IFacturaServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/inndata019/tienda")

public class FacturaController {
    private final IFacturaServices iFacturaServices;

    public FacturaController(IFacturaServices iFacturaServices) {
        this.iFacturaServices = iFacturaServices;
    }

    @GetMapping("/Factura/")
    public List<FacturaEntity>readAll() {return iFacturaServices.readALL();}

    @GetMapping("/Factura/{id}")
      public FacturaEntity readById(@PathVariable Integer id) {
       return iFacturaServices.readbyId(id)
                .orElseThrow(() -> new NoSuchElementException("Inventario no encontrado con id: " + id));
   }

  @PostMapping("/Factura")
 public FacturaEntity create(@RequestBody FacturaEntity facturaEntity){
       return iFacturaServices.create(facturaEntity);
    }

   @PutMapping("/Factura")
   public FacturaEntity update(@RequestBody FacturaEntity facturaEntity ){
        return  iFacturaServices.update(facturaEntity);
   }

  @DeleteMapping("/Factura/{id}")
   public String delete(@PathVariable Integer id){
       return iFacturaServices.delateById(id);
   }


}

