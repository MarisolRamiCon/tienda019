package com.example.tienda19.service;

import com.example.tienda19.entity.DetallesPedidoEntity;

import java.util.List;

public interface IDetallesPedidoService {
    //public List<DetallesPedidoEntity> readAll();
    //Buscar por id a los Detalles Pedido
    public DetallesPedidoEntity readById(Integer id);
    //Metodos CRUD C-CREATE, R-READ, U - UPDATE, D- DELETE
    public DetallesPedidoEntity create(DetallesPedidoEntity detallesPedido);
    public String updateById(Integer id, DetallesPedidoEntity detallesPedido );
    public String deleteById(Integer id);
}

