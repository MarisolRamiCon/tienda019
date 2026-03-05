package com.example.tienda19.service.impl;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.repository.FacturaRepository;
import com.example.tienda19.service.IFacturaServices;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@Transactional

public class FacturaService implements IFacturaServices {
    private final FacturaRepository facturaRepository;
    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override

    public List<FacturaEntity> readALL() {
        log.info("Se consultó exitosamente las facturas");

        return facturaRepository.findAll()
                .stream()
                .filter(factura ->
                        factura.getRazonsocial() != null &&
                                !factura.getRazonsocial().isEmpty() &&
                                factura.getRegimensocial() != null &&
                )
                .toList();
    }




    @Override
        return facturaRepository.findById(id);
    }




    @Override

        FacturaEntity existente = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));


    }
    @Override
        FacturaEntity existente = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));

        facturaRepository.delete(existente);

