package com.example.tienda19.service.impl;

import com.example.tienda19.entity.ProveedoresEntity;
import com.example.tienda19.repository.ProveedoresRepository;
import com.example.tienda19.service.IProveedoresService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProveedoresService implements IProveedoresService {
    @Autowired
    ProveedoresRepository proveedoresRepository;

    @Override
    public List<ProveedoresEntity> readAll() {
        log.info("Se consulto exitosamente la lista de proveedores");
        return proveedoresRepository.findAll();
    }
}
