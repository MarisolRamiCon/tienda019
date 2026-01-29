package com.example.tienda19.service.impl;

import com.example.tienda19.entity.DetallesPedidoEntity;
import com.example.tienda19.repository.DetallesPedidoRepository;
import com.example.tienda19.service.IDetallesPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetallesPedidoService implements IDetallesPedidoService {
    @Autowired //Nos dice que vamos a hacer una inyeccion de dependencia
    DetallesPedidoRepository detallesPedidoRepository;


    public List<DetallesPedidoEntity> readAll() {
        return detallesPedidoRepository.findAll().stream().filter(detallesPedido -> detallesPedido.getActivo()).toList();
    }

    @Override
    public DetallesPedidoEntity readById(Integer id) {
        Optional<DetallesPedidoEntity> detallesPedidoABuscar= detallesPedidoRepository.findById(id);
        if(detallesPedidoABuscar.isPresent()){
            return detallesPedidoABuscar.get();
        }else{
            return null;
        }

    }

    @Override
    public DetallesPedidoEntity create(DetallesPedidoEntity detallesPedido) {
        detallesPedidoRepository.save(detallesPedido);
        return detallesPedido;
    }

    @Override
    public String updateById(Integer id, DetallesPedidoEntity detallesPedido) {
        Optional<DetallesPedidoEntity> detallesPedidoBuscado = detallesPedidoRepository.findById(id);
        if(detallesPedidoBuscado.isPresent()){
            DetallesPedidoEntity modificado= new DetallesPedidoEntity();
            modificado.setId(detallesPedido.getId());
            //modificado.setM2(detallesPedido.getM2());
            //modificado.setPrecio(detallesPedido.getPrecio());
            detallesPedidoRepository.save(modificado);
            return "Detalles de pedido actualizado";
        }else{
            return "Detalles de pedido no encontrado";
        }

    }

    @Override
    public String deleteById(Integer id) {
        Optional<DetallesPedidoEntity> detallesPedidoABorrar = detallesPedidoRepository.findById(id);
        if(detallesPedidoABorrar.isPresent()){
            DetallesPedidoEntity detallesPedido = detallesPedidoABorrar.get();
            detallesPedido.setActivo(false);
            detallesPedidoRepository.save(detallesPedido);
            return "Detalles de pedido borrado";
        }else{
            return "No hay Detalles del pedido";
        }

    }
}
