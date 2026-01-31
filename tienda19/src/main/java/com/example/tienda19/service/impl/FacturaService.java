package com.example.tienda19.service.impl;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.repository.FacturaRepository;
import com.example.tienda19.service.IFacturaServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class FacturaService implements IFacturaServices {
    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public List<FacturaEntity> readALL() {
        log.info("Se conculto extisamente las facturas");
        return facturaRepository.findAll();
    }
@Override
public Optional<FacturaEntity>readbyId(Integer id){
        return facturaRepository.findById(id);
}
@Override
    public FacturaEntity update(FacturaEntity facturaEntity){return facturaRepository.save(facturaEntity);
    }
    @Override
    public FacturaEntity create(FacturaEntity facturaEntity){return  facturaRepository.save(facturaEntity);}


   @Override
   public String delateById(Integer id){
    Optional<FacturaEntity> entityFacturaopti = facturaRepository.findById(id);
    if (entityFacturaopti.isPresent()) {
        FacturaEntity facturaEntity = entityFacturaopti.get();
        facturaRepository.save(facturaEntity);

        return "Factura desactivada correctamente";
    }
     else {
            return "Proveedor no encontrado";
        }

   }
}

