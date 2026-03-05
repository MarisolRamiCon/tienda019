package com.example.tienda19.service.impl;

import com.example.tienda19.entity.FacturaEntity;
import com.example.tienda19.repository.FacturaRepository;
import com.example.tienda19.service.IFacturaServices;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
                                !factura.getRazonsocial().isEmpty()
                )
                .toList();
    }




    @Override
    public Optional<FacturaEntity> readbyId(Integer id) {
        return facturaRepository.findById(id);
    }




    @Override
    public FacturaEntity create(FacturaEntity facturaEntity) {
        return facturaRepository.save(facturaEntity);
    }

    @Override
    public FacturaEntity update(Integer id, FacturaEntity facturaEntity) {

        FacturaEntity existente = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));

        existente.setRazonsocial(facturaEntity.getRazonsocial());
        existente.setCodigoPostal(facturaEntity.getCodigoPostal());
        existente.setRegimensocial(facturaEntity.getRegimensocial());
        existente.setMontoFactura(facturaEntity.getMontoFactura());

        return facturaRepository.save(existente);
    }

    @Override
    public void DelateById(Integer id) {
        FacturaEntity existente = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));

        facturaRepository.delete(existente);
    } }

