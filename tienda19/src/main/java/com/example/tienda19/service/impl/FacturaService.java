package com.example.tienda19.service.impl;

import com.example.tienda19.entity.FacturaEntity;

import com.example.tienda19.repository.FacturaRepository;
import com.example.tienda19.request.RequetsFactura;
import com.example.tienda19.response.ResponseFactura;
import com.example.tienda19.service.IFacturaServices;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@SuppressWarnings("ALL")
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
                                !factura.getRegimensocial().isEmpty()
                )
                .toList();
    }




    @Override
    public Optional<FacturaEntity> readById(long id) {
        return facturaRepository.findById(id);
    }




    @Override
    public ResponseFactura create(RequetsFactura requetsFactura) {
        // Convertir el request en entidad
        FacturaEntity factura = new FacturaEntity();
        factura.setRazonsocial(requetsFactura.getRazonsocial());
        factura.setCodigoPostal(requetsFactura.getCodigoPostal());
        factura.setRegimensocial(requetsFactura.getRegimensocial());
        factura.setMontoFactura(requetsFactura.getMontoFactura());

        // Guardar la entidad
        FacturaEntity facturaGuardada = facturaRepository.save(factura);

        // Convertir la entidad guardada en ResponseFactura
        ResponseFactura response = new ResponseFactura();
        response.setId(facturaGuardada.getId());
        response.setRazonsocial(facturaGuardada.getRazonsocial());
        response.setCodigoPostal(facturaGuardada.getCodigoPostal());
        response.setRegimensocial(facturaGuardada.getRegimensocial());
        response.setMontoFactura(facturaGuardada.getMontoFactura());

        return response;
    }

    public ResponseFactura update(long id, @NonNull RequetsFactura requetsFactura) {
        try {
            log.info("Actualizando factura con id: {}", id);

            FacturaEntity existente = facturaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));

            // Actualizamos con los datos del request
            existente.setCodigoPostal(requetsFactura.getCodigoPostal());
            existente.setRegimensocial(requetsFactura.getRegimensocial());
            existente.setMontoFactura(requetsFactura.getMontoFactura());
            existente.setRazonsocial(requetsFactura.getRazonsocial());

            // Guardamos los cambios
            FacturaEntity modificada = facturaRepository.save(existente);

            // Preparamos la respuesta
            ResponseFactura responseFactura = new ResponseFactura();
            responseFactura.setId(modificada.getId());
            responseFactura.setRazonsocial(modificada.getRazonsocial());
            responseFactura.setRegimensocial(modificada.getRegimensocial());
            responseFactura.setMontoFactura(modificada.getMontoFactura());
            responseFactura.setCodigoPostal(modificada.getCodigoPostal());

            log.info("Factura actualizada exitosamente con id: {}", id);
            return responseFactura;

        } catch (RuntimeException e) {
            log.error("Error al actualizar factura: {}", e.getMessage());
            throw e;
        }
    }
    @Override
    public void DelateById(long id) {
        FacturaEntity existente = facturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));


        facturaRepository.delete(existente);
    }

    @Override
    public List<FacturaEntity> findByMontoFacturaGreaterThanEqual(Double monto) {

        if (monto == null || monto < 0) {
            throw new IllegalArgumentException("El monto debe ser mayor o igual a 0");
        }

        List<FacturaEntity> facturas =
                facturaRepository.findByMontoFacturaGreaterThanEqual(monto);

        if (facturas.isEmpty()) {
            throw new RuntimeException("No se encontraron facturas con ese monto");
        }

        return facturas;
    }


}


