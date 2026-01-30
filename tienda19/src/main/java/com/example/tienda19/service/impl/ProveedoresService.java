package com.example.tienda19.service.impl;

import com.example.tienda19.entity.ProveedoresEntity;
import com.example.tienda19.repository.ProveedoresRepository;
import com.example.tienda19.service.IProveedoresService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProveedoresService implements IProveedoresService {
    @Autowired
    ProveedoresRepository proveedoresRepository;

    @Override
    public List<ProveedoresEntity> readAll() {
        log.info("Se consultó exitosamente la lista de proveedores");
        return proveedoresRepository.findAll()
                .stream()
                .filter(proveedor -> proveedor.getNombreEmpresa() != null && !proveedor.getNombreEmpresa().isBlank())
                .toList();
    }

    @Override
    public ProveedoresEntity readById(Integer id) {
        Optional<ProveedoresEntity> proveedoresABuscar = proveedoresRepository.findById(id);
        if (proveedoresABuscar.isPresent()) {
            log.info("provedor encontrado con id={}",id);
            return proveedoresABuscar.get();
        } else {
            log.warn("provedor no encontrado con id={}",id);
            return null;
        }
    }

    @Override
    public ProveedoresEntity create(ProveedoresEntity proveedores) {
        proveedoresRepository.save(proveedores);
        log.info("proveedor creado exitosamente");
        return proveedores;
    }

    @Override
    public String update(Integer id, ProveedoresEntity proveedores) {
        Optional<ProveedoresEntity> proveedoresABuscar = proveedoresRepository.findById(id);
        if (proveedoresABuscar.isPresent()) {
            ProveedoresEntity existente = proveedoresABuscar.get();
            log.info("proveedor encontrado exitosamente",existente);
            existente.setNombreEmpresa(proveedores.getNombreEmpresa());
            existente.setContacto(proveedores.getContacto());
            existente.setCorreoElectronico(proveedores.getCorreoElectronico());
            existente.setTelefono(proveedores.getTelefono());

            proveedoresRepository.save(existente);
            log.info("proveedor actualizado exitosamente");
            return "Proveedor actualizado";
        } else {
            log.warn("proveedor no encontrado con id={}",id);
            return "Proveedor no encontrado";
        }
    }
    public Boolean delete(Integer id) {
        Optional<ProveedoresEntity> proveedoresABorrar = proveedoresRepository.findById(id);
        if (proveedoresABorrar.isPresent()) {
            ProveedoresEntity proveedor = proveedoresABorrar.get();
            proveedor.setActivo(false); // baja lógica
            proveedoresRepository.save(proveedor);
            return true;
        } else {
            return false;
        }
    }

    public String obtnerContacto(Integer id) {
        return proveedoresRepository.findContactoById(id);
    }
}
