package com.example.tienda19.service.impl;

import com.example.tienda19.entity.DetallesPedidoEntity;
import com.example.tienda19.repository.DetallesPedidoRepository;
import com.example.tienda19.service.IDetallesPedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class DetallesPedidoService implements IDetallesPedidoService {

    @Autowired
    DetallesPedidoRepository detallesPedidoRepository;

    @Override
    public List<DetallesPedidoEntity> readAll() {
        log.info("Se consulto exitosamente la lista de los Detalles de Pedido");
        return detallesPedidoRepository.findAll();
    }
}
